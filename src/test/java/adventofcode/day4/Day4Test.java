package adventofcode.day4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class Day4Test {

    @Test
    @DisplayName("Test day 4")
    void testDay4() throws IOException {
        assertEquals(2, Day4.calculateContainedRanges());
    }
}