package org.acme.events;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
// TODO: Research testing and improve tests
@QuarkusTest
public class EmployeesResourceTest {

    @Test
    public void testEmployeesEndpoint() {
        given()
          .when().get("/employees")
          .then()
             .statusCode(200);
    }

}