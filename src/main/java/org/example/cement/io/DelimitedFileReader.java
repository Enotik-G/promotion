package org.example.cement.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DelimitedFileReader {
    private static final String SEPARATOR = "#";

    public List<String[]> readFile(Path file) throws IOException {
        List<String[]> lineOrders = new ArrayList<>();
        try(BufferedReader br = Files.newBufferedReader(file) ) {
            String line;
            while((line = br.readLine()) != null) {
                String[] parts = line.split(SEPARATOR);
                lineOrders.add(parts);
            }
        }
        return lineOrders;
    }

}
