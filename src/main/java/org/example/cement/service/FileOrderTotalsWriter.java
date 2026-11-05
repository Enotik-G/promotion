package org.example.cement.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Parameter;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.Map;

public class FileOrderTotalsWriter implements OrderTotalsWriter{

    @Override
    public void saveTotals(Map<String, BigDecimal> orders) {
        Path path = Path.of("src/main/java/org/example/cement/data/discountPriceForOrders/orders.txt");
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(path.toFile()))) {
            for (Map.Entry<String, BigDecimal> entry : orders.entrySet()) {
                String line = entry.getKey() + " - " + entry.getValue().toPlainString();
                bw.write(line);
                bw.newLine();
            }
            System.out.println("Заказы успешно записаны в файл");
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        } ;
    }
}
