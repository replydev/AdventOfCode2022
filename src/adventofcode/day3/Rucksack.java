package adventofcode.day3;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Rucksack {

    private final Set<Character> firstCompartment;
    private final Set<Character> secondCompartment;

    public Rucksack(String line) {
        final int length = line.length();
        if (length % 2 != 0) {
            throw new IllegalArgumentException("Chars has to be even to be split");
        }

        final int lastElementOfFirstCompartment = length / 2;

        // Create a list of deduplicated chars
        this.firstCompartment = line.chars()
                .limit(lastElementOfFirstCompartment)
                .mapToObj(i -> (char) i)
                .collect(Collectors.toSet());

        this.secondCompartment = line.chars()
                .skip(lastElementOfFirstCompartment)
                .mapToObj(i -> (char) i)
                .collect(Collectors.toSet());
    }

    public int calculate() {
        int tot = 0;
        for (Character c : firstCompartment) {
            if (secondCompartment.contains(c)) {
                int indexOfCharacter = calculateIndex(c);
                tot += indexOfCharacter;
            }
        }
        return tot;
    }

    private static int calculateIndex(char c) {
        if (Character.isUpperCase(c)) {
            return (int) c - 38;
        } else {
            return (int) c - 96;
        }
    }
}
