package org.example.cement.service;

import java.math.BigDecimal;
import java.util.Map;

public interface SafeOrders {
    void safeFile(Map<String, BigDecimal> orders);
}
