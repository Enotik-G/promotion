package org.example.cement.io;

import org.example.cement.model.Order;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PipeFileReader implements ReaderOrder {
    private static final String SEPARATOR = "\\|";

    public List<Order> readOrders(Path file) throws IOException {
        List<Order> orders = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line;
            while ((line = reader.readLine()) != null) {
                orders.add(parseLine(line));
            }
        }
        return orders;
    }

    private Order parseLine(String line) {
        String[] parts = line.split(SEPARATOR);

        if (parts.length != 3) {
            throw new IllegalArgumentException("Ожидалось 3 поля, полученый length: " + parts.length);
        }

        LocalDateTime date = LocalDateTime.parse(parts[0]);
        String company = parts[1];
        int weightKg = Integer.parseInt(parts[2]);
        return new Order(date, company, weightKg);
    }

}
