package adventofcode.day3;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Rucksack {

    private final Set<Character> firstCompartment;
    private final Set<Character> secondCompartment;

    private static final List<Character> alphabet = List.of(
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    );

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
            if (secondCompartment.contains(c)){
                int indexOfCharacter = alphabet.indexOf(c) + 1;
                tot += indexOfCharacter;
            }
        }
        return tot;
    }
}
