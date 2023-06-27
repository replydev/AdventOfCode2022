package adventofcode.day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day5 {

    public static String getLastElements(Path path) throws IOException {
        CargoCrane cargoCrane = CargoCrane.fromFile(path);

        Files.readAllLines(path)
                .stream()
                .dropWhile(s -> !s.isBlank())
                .skip(1)
                .map(Instruction::fromLine)
                .forEach(cargoCrane::executeInstruction);

        return cargoCrane.lastElements();
    }

}
