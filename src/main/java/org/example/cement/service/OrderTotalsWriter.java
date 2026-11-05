package org.example.cement.service;

import java.math.BigDecimal;
import java.util.Map;

public interface OrderTotalsWriter {
    void saveTotals(Map<String, BigDecimal> orders);
}
