package adventofcode.day3;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day3 {
    public static int calculateTotal() throws IOException {
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("src/test/resources/day3/rucksacks.txt"))) {
            String line;
            int tot = 0;
            while((line = bufferedReader.readLine()) != null) {
                tot += new Rucksack(line).calculate();
            }
            return tot;
        }
    }
}
