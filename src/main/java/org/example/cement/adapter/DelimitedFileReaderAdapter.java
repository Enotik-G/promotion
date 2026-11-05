package org.example.cement.adapter;

import org.example.cement.io.DelimitedFileReader;
import org.example.cement.io.ReaderOrder;
import org.example.cement.model.Order;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DelimitedFileReaderAdapter implements ReaderOrder {
    private final DelimitedFileReader delimitedFileReader;

    public DelimitedFileReaderAdapter(DelimitedFileReader delimitedFileReader) {
        this.delimitedFileReader = delimitedFileReader;
    }
    @Override
    public List<Order> readOrders(Path file) throws IOException {
        List<String[]> lines =  delimitedFileReader.readFile(file);
        List<Order> orders = new ArrayList<>();

        for (String[] line : lines) {
            orders.add(new Order(LocalDateTime.parse(line[0]), line[1], Integer.parseInt(line[2])));
        }
        return orders;
    }
}
