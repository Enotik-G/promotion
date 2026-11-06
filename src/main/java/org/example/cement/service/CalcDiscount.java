package org.example.cement.service;

import org.example.cement.model.Order;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface CalcDiscount {
    Map<String, BigDecimal> ordersWithDiscount(List<Order> orders);
}
