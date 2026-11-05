package io;

import org.example.cement.io.PipeFileReader;
import org.example.cement.model.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

public class FileReaderOrdersTest {

    @TempDir
    Path tempDir;

    @Test
    void readOrderFromFile() throws IOException {
        Path file = tempDir.resolve("orders.txt");
        Files.writeString(file, "2021-02-09T09:11:43|Pyramid|10100\n");
        List<Order> actual = new PipeFileReader().readOrders(file);

        Order expected = new Order(
                LocalDateTime.parse("2021-02-09T09:11:43"),
                "Pyramid",
                10100
        );

        Assertions.assertEquals(List.of(expected), actual);
    }

    @Test
    void readOrdersFromEmptyFile() throws IOException {
        Path file = Files.createFile(tempDir.resolve("orders.txt"));
        List<Order> res = new PipeFileReader().readOrders(file);

        Assertions.assertTrue(res.isEmpty());
    }

    @Test
    void readOrdersNoSuchDirectory() throws IOException {
        Path file = tempDir.resolve("mistake.txt");
        Assertions.assertThrows(IOException.class, () -> new PipeFileReader().readOrders(file));
    }

}
