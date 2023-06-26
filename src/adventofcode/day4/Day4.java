package adventofcode.day4;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day4 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("resources/day4/clean_ranges.txt"))) {
            int count = 0;
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 2) {
                    throw new RuntimeException("Illegal line: " + line);
                }
                Range firstRange = new Range(parts[0]);
                Range secondRange = new Range(parts[1]);
                if (firstRange.contains(secondRange) || secondRange.contains(firstRange)) {
                    count++;
                }
            }
            // Expected output should be 2
            System.out.printf("I found %d ranges", count);
        }
    }
}
