package adventofcode.day7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class Day7Test {

    @Test
    @DisplayName("Test Day7")
    void testDay7() throws IOException {
        assertEquals(95437, Day7.calculateSize(Paths.get("src/test/resources/day7/filesystem_input.txt")));
    }
}