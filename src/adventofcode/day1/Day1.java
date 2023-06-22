package adventofcode.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day1 {
    public static void main(String[] args) {
        Pair<Integer, Integer> result = readFromFile(Paths.get("resources/day1/elves_calories.txt"));
        System.out.printf("The most important elves is the %dth with %d calories", result.getKey(), result.getValue());
    }

    /*
        Five elves
        1000
        2000
        3000

        4000

        5000
        6000

        7000
        8000
        9000

        10000
         */
    public static Pair<Integer, Integer> readFromFile(Path path) {
        try(BufferedReader bufferedReader = Files.newBufferedReader(path)) {
            String line;
            int maxTot = 0;
            int maxCount = 0;
            int tot = 0;
            int count = 1;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.isBlank()){
                    int calories = Integer.parseInt(line);
                    tot += calories;
                }
                else {
                    if (tot > maxTot) {
                        maxTot = tot;
                        maxCount = count;
                    }
                    tot = 0;
                    count++;
                }
            }
            return new Pair<>(maxCount, maxTot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
