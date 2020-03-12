package org.acme.events;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.business.Aggregator;

@Path("/employees/report/pdf")
public class PdfReportResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getPdfReport() {
        Aggregator aggregator = new Aggregator();
        aggregator.buildPdfReport();
        return "hello";
    }
}