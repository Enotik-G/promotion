package io;

import org.example.cement.io.FileOrderTotalsWriter;
import org.example.cement.io.OrderTotalsWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileWriterOrdersTest {

    @TempDir
    Path tempDir;

    @Test
    void WriteOrderInFile() throws IOException {
        Path file = tempDir.resolve("orders.txt");
        Map<String, BigDecimal> expected = new HashMap<>();
        expected.put("Pyramid", BigDecimal.valueOf(13130000));
        expected.put("Ancillary", BigDecimal.valueOf(2232000));
        expected.put("Carryover", BigDecimal.valueOf(943233));

        OrderTotalsWriter ow = new FileOrderTotalsWriter();
        ow.saveTotals(expected, file);

        Assertions.assertEquals(List.of("Pyramid - 13130000", "Carryover - 943233", "Ancillary - 2232000"), Files.readAllLines(file));
    }
}
