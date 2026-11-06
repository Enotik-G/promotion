package org.example.cement.io;

import org.example.cement.model.Order;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface ReaderOrder {
    List<Order> readOrders(Path file) throws IOException;
}
