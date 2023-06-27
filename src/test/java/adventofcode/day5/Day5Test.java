package adventofcode.day5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day5Test {

    @Test
    @DisplayName("Test day5")
    void testDay5() throws IOException {
        assertEquals("CMZ", Day5.getLastElements(Paths.get("src/test/resources/day5/cargo_crane.txt")));
    }
}