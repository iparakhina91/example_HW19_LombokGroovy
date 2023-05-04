package api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;

public class GroovyTests {

    @Test
    @DisplayName("Проверка наличия в списке пользователя c конкретным email по запросу GET api/users")
    public void checkUserEmailGroovy() {
        // @formatter:off
        given()
                .spec(Specs.request)
                .when()
                .get("/users")
                .then()
                .log().body()
                .body("data.findAll{it.email =~/.*?@reqres.in/}.email.flatten()",
                        hasItem("tracey.ramos@reqres.in"));
        // @formatter:on
    }

    @Test
    @DisplayName("Проверка наличия в списке пользователя c конкретным first_name по запросу GET api/users")
    public void checkUserFirstNameGroovy() {
        // @formatter:off
        given()
                .spec(Specs.request)
                .when()
                .get("/users")
                .then()
                .log().body()
                .body("data.findAll{it.first_name =~/[a-zA-Z]+/}.first_name.flatten()",
                        hasItem("Eve"));
        // @formatter:on
    }

    @Test
    @DisplayName("Проверка наличия в списке пользователя c конкретным last_name по запросу GET api/users")
    public void checkUserLastNameGroovy() {
        // @formatter:off
        given()
                .spec(Specs.request)
                .when()
                .get("/users")
                .then()
                .log().body()
                .body("data.findAll{it.last_name =~/[a-zA-Z]+/}.last_name.flatten()",
                        hasItem("Ramos"));
        // @formatter:on
    }
}