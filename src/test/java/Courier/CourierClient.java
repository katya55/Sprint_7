package Courier;

import Config.Client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import java.net.HttpURLConnection;
import java.util.Map;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CourierClient extends Client {


    @Step("Создать курьера")
    public ValidatableResponse createCourier(Courier courier) {
        return spec()
                .body(courier)
                .when()
                .post("/courier")
                .then().log().all();
    }

    @Step("Курьер создан")
    public void checkCreated(ValidatableResponse createResponse) {
        boolean create = createResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_CREATED)
                .extract()
                .path("ok");
        assertTrue(create);
    }

    @Step("Курьер авторизовывается")
    public ValidatableResponse logIn(Creds creds) {
        return spec()
                .body(creds)
                .when()
                .post("/courier/login")
                .then().log().all();
    }

    @Step("Курьер залогинился")
    public Integer checkLogin(ValidatableResponse loginResponse) {
        return loginResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .path("id");
    }


    @Step("Удалить курьера")
    public void delete(int courierId) {
        spec()
                .body(Map.of("id", courierId))
                .when()
                .delete("/courier/" + courierId)
                .then().log().all();
    }

    @Step("Создание дубликата курьера")
    public ValidatableResponse createDuplicateCourier(Courier courier) {
        spec()
                .body(courier)
                .when()
                .post("/courier")
                .then().log().all()
                .statusCode(HttpURLConnection.HTTP_CREATED);

        return spec()
                .body(courier)
                .when()
                .post("/courier")
                .then()
                .log().all();
    }


    @Step("Ошибка при создании двух одинаковых курьеров")
    public void checkErrorCreateDuplicateCourier(ValidatableResponse createResponse) {
        createResponse.assertThat()
                .statusCode(HttpURLConnection.HTTP_CONFLICT)
                .body("message", equalTo("Этот логин уже используется"));
    }

    @Step("Проверка обязательности полей при создании курьера")
    public void checkRequiredFieldsCreateCourier(ValidatableResponse createResponse) {
        createResponse.assertThat()
                .statusCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Step("Проверка обязательности полей при логине курьера")
    public void checkRequiredFields(ValidatableResponse createResponse) {
        createResponse.assertThat()
                .statusCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Step("Учетная запись не найдена")
    public void checkWrongLoginOrPassword(ValidatableResponse response) {
        response.assertThat()
                .statusCode(HttpURLConnection.HTTP_NOT_FOUND)
                .body("message", equalTo("Учетная запись не найдена"));
    }
}
