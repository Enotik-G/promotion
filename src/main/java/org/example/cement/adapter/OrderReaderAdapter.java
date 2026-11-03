package org.example.cement.adapter;

import org.example.cement.io.DelimitedFileReader;
import org.example.cement.io.ReaderOrder;
import org.example.cement.model.Order;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class OrderReaderAdapter implements ReaderOrder {
    private final DelimitedFileReader delimitedFileReader;

    public OrderReaderAdapter(DelimitedFileReader delimitedFileReader) {
        this.delimitedFileReader = delimitedFileReader;
    }
    @Override
    public List<Order> readOrders(Path file) throws IOException {
        List<String> orders =  delimitedFileReader.readFile(file);

    }
}
