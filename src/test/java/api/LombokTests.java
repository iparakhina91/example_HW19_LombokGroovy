package api;

import api.models.UserData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LombokTests {

    @Test
    @DisplayName("Создание пользователя POST api/users с валидными name и job")
    void createUserLombokTest() {
        String body = "{ \"name\": \"irina\", \"job\": \"qa\" }";

        given()
                .spec(Specs.request)
                .body(body)
                .when()
                .post("/users")
                .then()
                .log().body()
                .spec(Specs.responseCreate)
                .body("name", is("irina"))
                .body("job", is("qa"));
    }

    @Test
    @DisplayName("Изменение имени пользователя PUT api/users/:id с существующим id")
    void updateUserLombokTest() {
        String body = "{ \"name\": \"ira\", \"job\": \"qa\" }";

        given()
                .spec(Specs.request)
                .body(body)
                .when()
                .put("/users/6")
                .then()
                .log().body()
                .spec(Specs.response)
                .body("name", is("ira"));
    }

    @Test
    @DisplayName("Удаление пользователя DELETE api/users/:id с существующим id")
    void deleteUserLombokTest() {
        given()
                .spec(Specs.request)
                .when()
                .delete("/users/10")
                .then()
                .spec(Specs.responseDelete);
    }

    @Test
    @DisplayName("Проверка корректности данных существующего пользователя по запросу GET api/users/:id")
    void сheckSingleUserLombokTest() {
        UserData data = Specs.request
                .when()
                .get("/users/10")
                .then()
                .spec(Specs.response)
                .log().body()
                .extract().as(api.models.UserData.class);

        assertEquals(10, data.getUser().getId());
        assertEquals("byron.fields@reqres.in", data.getUser().getEmail());
        assertEquals("Byron", data.getUser().getFirstName());
        assertEquals("Fields", data.getUser().getLastName());
    }

    @Test
    @DisplayName("Регистрация пользователя POST api/register без password")
    void registerMissingPasswordLombokTest() {
        String body = "{ \"email\": \"irina@mail.ru\" }";

        given()
                .spec(Specs.request)
                .body(body)
                .when()
                .post("/register")
                .then()
                .log().body()
                .spec(Specs.responseFail)
                .body("error", is("Missing password"));
    }
}
