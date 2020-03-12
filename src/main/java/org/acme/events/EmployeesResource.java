package org.acme.events;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.acme.business.Aggregator;
import org.acme.dto.EmployeeDto;

@Path("/employees")
public class EmployeesResource {

    @GET
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmployees() {
        Aggregator aggregator = new Aggregator();
        List<EmployeeDto> employees = aggregator.getAllEmployees();
        //TODO: fix this check makes no sense.
        if (employees != null)
            return Response.ok(employees).build();            
        return Response.noContent().build();
    }
}