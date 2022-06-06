package dev.bstk.gatwayapi.api;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class ApisResourceTest {

    @Test
    @Disabled
    void apis() {
        given()
            .when().get("/okk-gatway/apis")
            .then()
            .statusCode(200)
            .body(is("Consulta Api"));
    }
}
