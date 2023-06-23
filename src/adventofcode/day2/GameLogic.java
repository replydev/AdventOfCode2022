package adventofcode.day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class GameLogic {
    private final Path path;

    private static final Map<String, GameElement> stringMappings = Map.of(
            "A", GameElement.ROCK,
            "B", GameElement.PAPER,
            "C", GameElement.SCISSORS,
            "X", GameElement.ROCK,
            "Y", GameElement.PAPER,
            "Z", GameElement.SCISSORS
    );

    public GameLogic(Path path) {
        this.path = path;
    }

    public int calculateScore() throws IOException {
        try(BufferedReader bufferedReader = Files.newBufferedReader(path)){
            String line;
            int tot = 0;
            while ((line = bufferedReader.readLine()) != null) {
                tot += calculateLine(line);
            }
            return tot;
        }
    }

    private static int calculateLine(String line) {
        if (line == null){
            throw new IllegalArgumentException("Line should not be null");
        }

        String[] parts = line.split(" ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid line");
        }

        GameElement opponent = stringMappings.get(parts[0]);
        GameElement player = stringMappings.get(parts[1]);

        return GameElement.calculateMatch(player, opponent) + player.getElementScore();
    }

}
