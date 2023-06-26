package adventofcode.day2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class Day2Test {

    @Test
    @DisplayName("Test day 2")
    void testDay2() throws IOException {
        GameLogic gameLogic = new GameLogic(Paths.get("src/test/resources/day2/rps_strategy.txt"));
        assertEquals(15, gameLogic.calculateScore());
    }
}