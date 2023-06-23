package adventofcode.day2;

import java.io.IOException;
import java.nio.file.Paths;

public class Day2 {

    public static void main(String[] args) throws IOException {
        GameLogic gameLogic = new GameLogic(Paths.get("resources/day2/rps_strategy.txt"));
        // Expected output should be 15
        System.out.printf("The player has made %d points", gameLogic.calculateScore());
    }
}
