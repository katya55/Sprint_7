import Courier.Courier;
import Courier.Creds;
import Courier.CourierClient;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class CourierTest {

    CourierClient courierClient = new CourierClient();
    int courierId;

    @AfterEach
    public void dropCourier() {
        if (courierId > 0) {
            courierClient.delete(courierId);
        }
    }

    //создание курьера
    @Test
    @DisplayName("Удачное создание курьера")
    public void createNewCourier() {
        var courier = Courier.generateRandomCourier();
        ValidatableResponse createResponse = courierClient.createCourier(courier);
        courierClient.checkCreated(createResponse);

        //логин/пароль
        var creds = Creds.getCreds(courier);
        ValidatableResponse loginResponse = courierClient.logIn(creds);
        courierId = courierClient.checkLogin(loginResponse);

        assertNotEquals(0, courierId);

    }

    @Test
    @DisplayName("Создание двух одинаковых курьеров")
    public void createDuplicateCourier() {
        var courier = Courier.generateRandomCourier();
        ValidatableResponse createResponse = courierClient.createDuplicateCourier(courier);
        courierClient.checkErrorCreateDuplicateCourier(createResponse);
    }

    @Test
    @DisplayName("Создание курьера без логина")
    public void createCourierWithoutLogin() {
        var courier = Courier.withNoLogin();
        ValidatableResponse createResponse = courierClient.createCourier(courier);
        courierClient.checkRequiredFieldsCreateCourier(createResponse);
    }

    @Test
    @DisplayName("Создание курьера без пароля")
    public void createCourierWithoutPassword() {
        var courier = Courier.withNoPassword();
        ValidatableResponse createResponse = courierClient.createCourier(courier);
        courierClient.checkRequiredFieldsCreateCourier(createResponse);
    }

    @Test
    @DisplayName("Логин курьера без логина")
    public void logCourierWithoutLogin() {
        Creds creds = Creds.WithoutLogin();
        ValidatableResponse createResponse = courierClient.logIn(creds);
        courierClient.checkRequiredFields(createResponse);
    }

    @Test
    @DisplayName("Логин курьера без пароля")
    public void logCourierWithoutPassword() {
        Creds creds = Creds.WithoutPassword();
        ValidatableResponse createResponse = courierClient.logIn(creds);
        courierClient.checkRequiredFields(createResponse);
    }

    @Test
    @DisplayName("Логин курьера без логина и пароля")
    public void logCourierWithoutLoginAndPassword() {
        Creds creds = Creds.empty();
        ValidatableResponse createResponse = courierClient.logIn(creds);
        courierClient.checkRequiredFields(createResponse);
    }

    @Test
    @DisplayName("Логин курьера с неправильным логином")
    public void loginWithWrongLogin() {
        Courier courier = Courier.generateRandomCourier();
        Creds incorrectLogin = new Creds("qwert", courier.getPassword());
        ValidatableResponse createResponse = courierClient.logIn(incorrectLogin);
        courierClient.checkWrongLoginOrPassword(createResponse);
    }

    @Test
    @DisplayName("Логин курьера с неправильным паролем")
    public void loginWithWrongPassword() {
        Courier courier = Courier.generateRandomCourier();
        Creds incorrectPassword = new Creds(courier.getLogin(), "4444");
        ValidatableResponse createResponse = courierClient.logIn(incorrectPassword);
        courierClient.checkWrongLoginOrPassword(createResponse);
    }


}
