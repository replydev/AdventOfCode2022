package adventofcode.day3;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day3 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("resources/day3/rucksacks.txt"))) {
            String line;
            int tot = 0;
            while((line = bufferedReader.readLine()) != null) {
                tot += new Rucksack(line).calculate();
            }
            // Total should be 157
            System.out.printf("The tot is %d\n", tot);
        }
    }
}
