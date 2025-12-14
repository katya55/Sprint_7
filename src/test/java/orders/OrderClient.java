package Orders;

import config.Client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;

public class OrderClient extends Client {


    @Step("Создание заказа")
    public ValidatableResponse createOrder(Order order) {
        return spec()
                .body(order)
                .when()
                .post("/orders")
                .then()
                .log().all();
    }

    @Step("Заказ создан")
    public Integer checkOrderCreated(ValidatableResponse createResponse) {
        return createResponse.assertThat()
                .statusCode(HttpURLConnection.HTTP_CREATED)
                .extract()
                .path("track");
    }

    @Step("Получение списка заказов")
    public ValidatableResponse getListOfOrder() {
        return spec()
                .when()
                .get("/orders")
                .then()
                .log().all();
    }

    @Step("Проверка наличия тела ответа")
    public void checkBodyOrdersList(ValidatableResponse createResponse) {
        createResponse.assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .body("orders", notNullValue())       // поле есть
                .body("orders", instanceOf(List.class)) // действительно список
                .body("orders.size()", greaterThan(0)); // список не пустой
    }

    @Step("Удаление заказа")
    public void cancelOrder(int track) {
        spec()
                .body(Map.of("id", track))
                .when()
                .put("orders/cancel" + track)
                .then().log().all();
    }


}

