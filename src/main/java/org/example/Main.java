package org.example;

import org.example.cement.io.PipeFileReader;
import org.example.cement.model.Order;
import org.example.cement.service.DiscountService;
import org.example.cement.io.FileOrderTotalsWriter;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class Main {
    static void main() throws IOException {
        PipeFileReader pr = new PipeFileReader();
        DiscountService ds = new DiscountService();
        FileOrderTotalsWriter fotw = new FileOrderTotalsWriter();
        List<Order> orders = pr.readOrders(Path.of("src/main/java/org/example/cement/data/orders/discount_day.txt"));
        System.out.println(orders);
        Map<String, BigDecimal> res = ds.ordersWithDiscount(orders);
        fotw.saveTotals(res, Path.of("src/main/java/org/example/cement/data/discountPriceForOrders"));
        System.out.println(res);
    }
}
