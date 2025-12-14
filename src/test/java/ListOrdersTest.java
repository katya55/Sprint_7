import orders.OrderClient;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ListOrdersTest {
    OrderClient orderClient = new OrderClient();

    @Test
    @DisplayName("Получение списка заказов")
    public void getOrdersShouldReturnOrdersList() {
        ValidatableResponse createResponse = orderClient.getListOfOrder();
        orderClient.checkBodyOrdersList(createResponse);
    }

}
