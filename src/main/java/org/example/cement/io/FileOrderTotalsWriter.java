package org.example.cement.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.Map;

public class FileOrderTotalsWriter implements OrderTotalsWriter {
    private static final String OUTPUT_SEPARATOR = " - ";

    @Override
    public void saveTotals(Map<String, BigDecimal> orders, Path path) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path.toFile()))) {
            for (Map.Entry<String, BigDecimal> entry : orders.entrySet()) {
                String line = entry.getKey() + OUTPUT_SEPARATOR + entry.getValue().toPlainString();
                bw.write(line);
                bw.newLine();
            }
            System.out.println("Заказы успешно записаны в файл");
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
        ;
    }
}
