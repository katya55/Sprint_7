import Courier.Courier;
import Courier.Creds;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.HttpURLConnection;

import static io.restassured.RestAssured.given;

public class MainTest {

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    //создание курьера
    @Test
    public void createNewCourier() {
        var courier = Courier.generateRandomCourier();
      boolean created = createCourier(courier)
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_CREATED)
                .extract()
                .path("ok")
              ;


        //логин/пароль
        var creds = Creds.getCreds(courier);
        int id = given()
                .contentType(ContentType.JSON)
                .auth().oauth2("подставь_сюда_свой_токен")
                .and()
                .body(creds)
                .when()
                .post("/api/v1/courier/login")
                .then().statusCode(HttpURLConnection.HTTP_OK)
                .and()
                .extract()
                .path("ok");

    }

    private static ValidatableResponse createCourier(Courier.Courier courier) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .body(courier)
                .when()
                .post("/api/v1/courier")
                .then().log().all();
    }
}
