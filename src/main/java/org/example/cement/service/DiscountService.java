package org.example.cement.service;

import org.example.cement.model.Order;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscountService implements CalcDiscount {
    private static final BigDecimal START_DISCOUNT_PERCENT = BigDecimal.valueOf(50);
    private static final BigDecimal PRICE_PER_KG = BigDecimal.valueOf(2000);
    private static final BigDecimal DISCOUNT_STEP_PERCENT = BigDecimal.valueOf(5);

    @Override
    public Map<String, BigDecimal> ordersWithDiscount(List<Order> orders) {
        orders.sort(Comparator.comparing(Order::getDate));
        BigDecimal discountPercent = START_DISCOUNT_PERCENT;

        Map<String, BigDecimal> resOrders = new HashMap<>();

        for (Order order : orders) {
            BigDecimal price = calculateOrderCost(order.getWeightKg(), discountPercent);
            discountPercent = discountPercent.subtract(DISCOUNT_STEP_PERCENT).max(BigDecimal.ZERO);
            resOrders.merge(order.getCompany(), price, BigDecimal::add);
        }

        return resOrders;
    }

    private BigDecimal calculateOrderCost(int weightKg, BigDecimal discountPercent) {
        return BigDecimal.valueOf(weightKg).multiply(DiscountService.PRICE_PER_KG).multiply(BigDecimal.valueOf(100)
                .subtract(discountPercent)).divide(BigDecimal.valueOf(100));
    }
}
