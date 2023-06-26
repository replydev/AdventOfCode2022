package adventofcode.day3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class Day3Test {

    @Test
    @DisplayName("Test day3")
    void testDay3() throws IOException {
        assertEquals(157, Day3.calculateTotal());
    }
}