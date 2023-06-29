package adventofcode.day7;

import java.io.IOException;
import java.nio.file.Path;

public class Day7 {

    public static int calculateSize(Path path) throws IOException {
        return new Parser(path).calculateSizeMost10000();
    }
}
