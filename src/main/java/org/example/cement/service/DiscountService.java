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

public class DiscountService implements CalcDiscount {


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
}
