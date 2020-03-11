package org.acme.events;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.business.ReportManager;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        ReportManager manager = new ReportManager();
        JasperReport report = manager.loadTemplate();
        JasperPrint print = manager.addEmployeeData(report);
        manager.exportToPDF(print);
        manager.exportToXls(print);
        return "hello";
    }
}