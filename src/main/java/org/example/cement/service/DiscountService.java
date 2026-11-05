package org.example.cement.service;

import org.example.cement.model.Order;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscountService implements CalcDiscount, SafeOrders {


    @Override
    public Map<String, BigDecimal> ordersWithDiscount(List<Order> orders) {
        orders.sort(Comparator.comparing(Order::getDate));
        int discountPercent = 50;
        int pricePerKg = 2000;

        Map<String, BigDecimal> resOrders = new HashMap<>();

        for (Order order : orders) {
            BigDecimal price = calculateOrderCost(
                    order.getWeightKg(), pricePerKg, discountPercent);
            discountPercent = Math.max(0, discountPercent - 5);
            resOrders.merge(order.getCompany(), price, BigDecimal::add);
        }

        return resOrders;
    }

    private BigDecimal calculateOrderCost(
            int weightKg, int pricePerKg, int discountPercent) {
        return BigDecimal.valueOf(weightKg)
                .multiply(BigDecimal.valueOf(pricePerKg))
                .multiply(BigDecimal.valueOf(100 - discountPercent))
                .divide(BigDecimal.valueOf(100));
    }


    @Override
    public void safeFile(Map<String, BigDecimal> orders) {
        String path = "src/main/java/org/example/cement/data/discountPriceForOrders/orders.txt";
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (Map.Entry<String, BigDecimal> entry : orders.entrySet()) {
                String line = entry.getKey() + " - " + entry.getValue();

                bw.write(line);
                bw.newLine();
            }
            System.out.println("Заказы успешно записаны в файл");
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        } ;
    }
}
