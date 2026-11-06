package io;

import org.example.cement.adapter.DelimitedFileReaderAdapter;
import org.example.cement.io.DelimitedFileReader;
import org.example.cement.model.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

public class DelimitedFileReaderAdapterTest {

    @TempDir
    Path tempDir;

    @Test
    void readOrderFromFileWithSeparatorHash() throws IOException {
        Path file = tempDir.resolve("sharpSeparator.txt");
        Files.writeString(file, "2021-02-09T09:11:43#Pyramid#10100");

        List<Order> actual = new DelimitedFileReaderAdapter(new DelimitedFileReader()).readOrders(file);

        Order expected = new Order(LocalDateTime.parse("2021-02-09T09:11:43"), "Pyramid", 10100);

        Assertions.assertEquals(List.of(expected), actual);
    }

    @Test
    void readOrdersAdapterFromEmptyFile() throws IOException {
        Path file = Files.createFile(tempDir.resolve("orders.txt"));
        List<Order> res = new DelimitedFileReaderAdapter(new DelimitedFileReader()).readOrders(file);
        Assertions.assertTrue(res.isEmpty());
    }

    @Test
    void readOrdersAdapterFromOrdersNoSuchDirectory() {
        Path file = tempDir.resolve("mistake.txt");
        Assertions.assertThrows(IOException.class, () -> new DelimitedFileReaderAdapter(new DelimitedFileReader()).readOrders(file));
    }


}
