package adventofcode.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Elves implements Comparable<Elves>{
    private int calories;

    public int getCalories() {
        return calories;
    }

    public Elves() {
        this.calories = 0;
    }

    public void append(int calories) {
        this.calories += calories;
    }

    @Override
    public int compareTo(Elves o) {
        // Inverted to sort desc
        return Integer.compare(o.calories, calories);
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
    public static List<Elves> readFromFile(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        // Elves count is equal every space plus one
        long elvesCount = lines.stream().filter(String::isBlank).count() + 1;
        List<Elves> elvesList = new ArrayList<>((int) elvesCount);
        try(BufferedReader bufferedReader = Files.newBufferedReader(path)) {
            String line;
            Elves elves = new Elves();
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.isBlank()){
                    int calories = Integer.parseInt(line);
                    elves.append(calories);
                }
                else {
                    elvesList.add(elves);
                    elves = new Elves();
                }
            }
            elvesList.add(elves);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return elvesList;
    }
}
