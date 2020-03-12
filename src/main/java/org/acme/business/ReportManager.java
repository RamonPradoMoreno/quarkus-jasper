package org.acme.business;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.acme.entities.Employee;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRSaver;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

/**
 * ReportManager
 */
public class ReportManager {

    private JasperReport report = null;

    public ReportManager() {
        super();
    }

    public JasperReport loadTemplate() {
        // Load the report template
        final InputStream employeeReportStream = getClass().getResourceAsStream("/report-templates/generated-report.jrxml");
		try {
            //Compile the report.
            JasperReport jasperReport = JasperCompileManager.compileReport(employeeReportStream);
            this.report = jasperReport;
        // Save the compiled report.
        // TODO: Maybe it can be saved with the other reports? Like in the export methods.
        JRSaver.saveObject(jasperReport, "saved-report.jasper");
		} catch (JRException ex) {
			ex.printStackTrace();
        }
        return this.report;
    }
    public JasperPrint addEmployeeData(JasperReport report) {
        List<Employee> allEmployees = Employee.findAll().list();
        JRDataSource conn = new JRBeanCollectionDataSource(allEmployees);
        JasperPrint jasperPrint = null;
        try {
			jasperPrint = JasperFillManager.fillReport(report, null,conn);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
        }

        return jasperPrint;
    }
    public void exportToPDF(JasperPrint print, String destinationDir) {
        JRPdfExporter exporter = new JRPdfExporter();
 
        exporter.setExporterInput(new SimpleExporterInput(print));
        String destinationPath = buildDestinationPath(destinationDir, ".pdf");
        exporter.setExporterOutput(
        new SimpleOutputStreamExporterOutput(destinationPath));
        
        SimplePdfReportConfiguration reportConfig
        = new SimplePdfReportConfiguration();
        reportConfig.setSizePageToContent(true);
        reportConfig.setForceLineBreakPolicy(false);
        
        SimplePdfExporterConfiguration exportConfig
        = new SimplePdfExporterConfiguration();
        exportConfig.setMetadataAuthor("baeldung");
        exportConfig.setEncrypted(true);
        exportConfig.setAllowedPermissionsHint("PRINTING");
        
        exporter.setConfiguration(reportConfig);
        exporter.setConfiguration(exportConfig);
        
        try {
			exporter.exportReport();
		} catch (JRException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
                
    }
    public void exportToXls(JasperPrint print, String destinationDir) {
        JRXlsxExporter exporter = new JRXlsxExporter();
        
        exporter.setExporterInput(new SimpleExporterInput(print));
        String destinationPath = buildDestinationPath(destinationDir, ".xls");
        exporter.setExporterOutput(
        new SimpleOutputStreamExporterOutput(destinationPath));

        SimpleXlsxReportConfiguration reportConfig
        = new SimpleXlsxReportConfiguration();
        reportConfig.setSheetNames(new String[] { "Employee Data" });
        exporter.setConfiguration(reportConfig);
        try {
			exporter.exportReport();
		} catch (JRException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
        
    }
    private String buildDestinationPath(String directory, String extension)
    {
        File dir = new File(directory);
        // TODO: control possible permission exceptions
        if (!dir.exists()) dir.mkdirs();
        String fileName = new SimpleDateFormat("yyyy_MM_dd-HH_mm_ss").format(new Date());
        return directory +"/" + fileName + extension;
    }

}