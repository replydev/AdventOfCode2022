package adventofcode.day1;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Day1 {
    public static void main(String[] args) throws IOException {
        List<Elves> elvesList = Elves.readFromFile(Paths.get("resources/day1/elves_calories.txt"));
        Pair<Integer, Elves> result = IntStream.range(0, elvesList.size())
                .mapToObj(i -> new Pair<>(i, elvesList.get(i)))
                .max((p1, p2) ->
                        p2.getValue().compareTo(p1.getValue())
                )
                .orElseThrow();
        System.out.printf("The most important elves is the %dth with %d calories", result.getKey() + 1, result.getValue().getCalories());
    }
}
