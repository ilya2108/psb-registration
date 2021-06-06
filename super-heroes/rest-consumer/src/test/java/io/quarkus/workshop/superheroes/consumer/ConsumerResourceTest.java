package io.quarkus.workshop.superheroes.consumer;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ConsumerResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/api/consumer")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

}