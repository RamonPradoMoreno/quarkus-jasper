package org.acme.business;

import java.util.ArrayList;
import java.util.List;

import org.acme.dto.EmployeeDto;
import org.acme.entities.Employee;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 * Aggregator
 */
public class Aggregator {
    ReportManager manager = new ReportManager();
    // TODO: dir should be configurable. Tried Injection did not work maybe save it in resources?
    String reportsSaveDir = "/home/rpradom/personalspace/quarkus-jasper/generated-reports";
    public Aggregator() {
        super();
    }
    
    public void buildPdfReport() {
        JasperReport report = manager.loadTemplate();
        JasperPrint print = manager.addEmployeeData(report);
        manager.exportToPDF(print, reportsSaveDir);
    }
    public void buildXlsReport() {
        JasperReport report = manager.loadTemplate();
        JasperPrint print = manager.addEmployeeData(report);
        manager.exportToXls(print, reportsSaveDir);
    }
    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeDto> employees = new ArrayList<EmployeeDto>();
        List<Employee> allEmployeeEntities = Employee.findAll().list();
        for (Employee employee : allEmployeeEntities){
            employees.add(EmployeeAdapter.adapt(employee));
        }
        return employees;

    }
}