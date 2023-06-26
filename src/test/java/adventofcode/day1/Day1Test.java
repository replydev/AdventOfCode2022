package adventofcode.day1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class Day1Test {

    @Test
    @DisplayName("Test Day1")
    void testDay1() {
        Pair<Integer, Integer> result = Day1.readFromFile(Paths.get("src/test/resources/day1/elves_calories.txt"));
        assertEquals(4, result.getKey());
        assertEquals(24000, result.getValue());
    }
}