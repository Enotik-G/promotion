package org.example;

import org.example.cement.io.PipeFileReader;
import org.example.cement.model.Order;
import org.example.cement.service.DiscountService;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class Main {
    static void main() throws IOException {
        PipeFileReader pr = new PipeFileReader();
        DiscountService ds = new DiscountService();

        List<Order> orders = pr.readOrders(Path.of("src/main/java/org/example/cement/data/orders/discount_day.txt"));
        System.out.println(orders);
        Map<String, BigDecimal> res = ds.ordersWithDiscount(orders);
        ds.safeFile(res);
        System.out.println(res);
    }
}
