package adventofcode.day5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Instruction(int elementsToMove, int from, int to) {

    private final static Pattern linePattern = Pattern.compile("^move (\\d+) from (\\d+) to (\\d+)$");

    public static Instruction fromLine(String line) {
        if (line == null) {
            throw new IllegalArgumentException("Line should not be null");
        }

        Matcher matcher = linePattern.matcher(line.trim());
        if (!matcher.find()) {
            throw new IllegalArgumentException("Invalid instruction: " + line);
        }

        // Should not throw NumberFormatException as verified by regex
        int elementsToMove = Integer.parseInt(matcher.group(1));
        int from = Integer.parseInt(matcher.group(2));
        int to = Integer.parseInt(matcher.group(3));

        return new Instruction(elementsToMove, from, to);
    }
}
