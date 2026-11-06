package service;

import org.example.cement.model.Order;
import org.example.cement.service.DiscountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalcDiscountOrdersTest {

    @Test
    void toMapOrdersWithCalcDiscount() {
        DiscountService ds = new DiscountService();

        List<Order> orders = new ArrayList<>();
        orders.add(new Order(LocalDateTime.parse("2021-02-09T12:32:48"), "Electricity", 3680));
        orders.add(new Order(LocalDateTime.parse("2021-02-09T21:10:34"), "Ancillary", 30000));
        orders.add(new Order(LocalDateTime.parse("2021-02-09T09:11:43"), "Electricity", 10100));


        Map<String, BigDecimal> expected = new HashMap<>();
        expected.put("Electricity", BigDecimal.valueOf(14148000));
        expected.put("Ancillary", BigDecimal.valueOf(36000000));

        Map<String, BigDecimal> actual = ds.ordersWithDiscount(orders);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void zeroDiscountTest() {
        List<Order> orders = new ArrayList<>();
        LocalDateTime start = LocalDateTime.of(2021, 2, 9, 10, 0);

        for (int i = 0; i < 12; i++) {
            orders.add(new Order(start.plusMinutes(i), "Pyramid", 1));
        }

        Map<String, BigDecimal> expected =
                Map.of("Pyramid", BigDecimal.valueOf(18500));

        Map<String, BigDecimal> actual =
                new DiscountService().ordersWithDiscount(orders);

        Assertions.assertEquals(expected, actual);
    }
}
