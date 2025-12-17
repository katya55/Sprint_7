import orders.Order;
import orders.OrderClient;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class OrderTest {
    OrderClient orderClient = new OrderClient();
    int track;

    @AfterEach
    public void dropOrders() {
        if (track > 0) {
            orderClient.cancelOrder(track);
        }
    }

    @ParameterizedTest
    @DisplayName("Создание заказов")
    @MethodSource("colors")
    public void createOrderShouldReturnTrack(List<String> color) {
        var order = Order.generateOrder();
        order.setColor(color);
        ValidatableResponse createResponse = orderClient.createOrder(order);
        track = orderClient.checkOrderCreated(createResponse);
        assertNotEquals(0, track);
    }

    static Stream<Arguments> colors() {
        return Stream.of(
                Arguments.of(List.of("BLACK")),
                Arguments.of(List.of("GREY")),
                Arguments.of(List.of("BLACK", "GREY")),
                Arguments.of((List<String>) null)
        );
    }

}


