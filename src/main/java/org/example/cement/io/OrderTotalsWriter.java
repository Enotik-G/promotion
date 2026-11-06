package org.example.cement.io;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.Map;

public interface OrderTotalsWriter {
    void saveTotals(Map<String, BigDecimal> orders, Path path);
}
