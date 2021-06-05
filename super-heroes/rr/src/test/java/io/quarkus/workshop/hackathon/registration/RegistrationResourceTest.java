package io.quarkus.workshop.hackathon.registration;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class RegistrationResourceTest {

    // @Test
    // public void testCreateEndpoint() {
    //     given()
    //       .when().get("/api/registration")
    //       .then()
    //          .statusCode(200)
    //          .body(is("hello hero"));
    // }

}