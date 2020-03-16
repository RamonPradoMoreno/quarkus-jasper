package org.acme.events;

import java.io.ByteArrayOutputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.acme.business.Aggregator;

@Path("/employees/report/xls")
public class XlsReportResource {

    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getXlsReport() {
        Aggregator aggregator = new Aggregator();
        ByteArrayOutputStream baos = aggregator.buildXlsReport();
        byte[] bytes = baos.toByteArray();
        if(baos.size() != 0 && baos != null){
            return Response.ok(bytes).type("application/xls").header("Content-Disposition",  "filename=sample_report.xls").build();
        }
        return Response.serverError().entity("Report is empty, maybe the database is empty.").build();
        
    }
}