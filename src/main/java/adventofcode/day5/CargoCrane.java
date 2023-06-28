package adventofcode.day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CargoCrane {

    private static final int CONTAINER_SIZE = 3;
    private final List<LinkedList<Character>> stacks;

    private CargoCrane(List<LinkedList<Character>> stacks) {
        this.stacks = stacks;
    }

    public void executeInstruction(Instruction instruction) {
        if (instruction == null) {
            throw new IllegalArgumentException("Instruction should not be null");
        }

        if (stacks.size() < Math.max(instruction.from(), instruction.to())) {
            throw new IllegalArgumentException("Instruction are out of bounds: " + instruction);
        }

        // Minus 1 cause index in file format starts with 1
        LinkedList<Character> fromLinked = stacks.get(instruction.from() - 1);
        LinkedList<Character> toLinked = stacks.get(instruction.to() - 1);

        for (int i = 0; i < instruction.elementsToMove(); i++) {
            if (fromLinked.isEmpty()) {
                throw new IllegalArgumentException(String.format("Cannot move %d to %d, no elements in stack", instruction.from(), instruction.to()));
            }
            toLinked.addLast(fromLinked.removeLast());
        }
    }

    public String lastElements() {
        StringBuilder stringBuilder = new StringBuilder(stacks.size());
        stacks.stream()
                .map(LinkedList::getLast)
                .forEach(stringBuilder::append);
        return stringBuilder.toString();
    }

    public static CargoCrane fromFile(Path path) throws IOException {
        if (path == null) {
            throw new IllegalArgumentException("Path should not be null");
        }

        List<String> stackLines = getStackLines(path);
        if (stackLines.size() < 2) {
            throw new IllegalArgumentException("Invalid input file");
        }

        int numberOfStacks = getNumberOfStacks(stackLines);
        final int maxLength = numberOfStacks * 3 + (numberOfStacks - 1);
        List<LinkedList<Character>> stacks = buildStackStructure(numberOfStacks);

        stackLines = addSpacesToLines(stackLines, maxLength);

        for (int i = stackLines.size() - 2; i >= 0; i--) {
            List<Optional<Character>> lineResult = parseLine(stackLines.get(i));
            if (lineResult.size() != numberOfStacks) {
                throw new IllegalArgumentException("Invalid line format");
            }

            for (int j = 0; j < numberOfStacks; j++) {
                final int finalJ = j;
                lineResult.get(j).ifPresent(c -> {
                    LinkedList<Character> linkedList = stacks.get(finalJ);
                    linkedList.add(c);
                });

            }
        }

        return new CargoCrane(stacks);
    }

    private static List<String> addSpacesToLines(List<String> stackLines, int length) {
        return stackLines.stream()
                .map(StringBuilder::new)
                .peek(s -> {
                    while (s.length() < length) {
                        s.append(' ');
                    }
                })
                .map(StringBuilder::toString)
                .toList();
    }

    private static List<LinkedList<Character>> buildStackStructure(Integer numberOfStacks) {
        List<LinkedList<Character>> stacks = new ArrayList<>(numberOfStacks);
        for (int i = 0; i < numberOfStacks; i++) {
            stacks.add(new LinkedList<>());
        }
        return stacks;
    }

    private static List<String> getStackLines(Path path) throws IOException {
        return Files.readAllLines(path)
                .stream()
                .takeWhile(s -> !s.isBlank())
                .toList();
    }

    private static int getNumberOfStacks(List<String> stackLines) {
        return Arrays.stream(stackLines.get(stackLines.size() - 1).split(" "))
                .filter(s -> !s.isBlank())
                .map(String::trim)
                .map(Integer::parseInt)
                .max(Integer::compareTo)
                .orElseThrow(() -> new IllegalArgumentException("Invalid input file"));
    }

    private static List<Optional<Character>> parseLine(String line) {
        if (line == null) {
            throw new IllegalArgumentException("Line should not be null");
        }

        List<Optional<Character>> fetched = new ArrayList<>();
        int takenCount = 0;
        StringBuilder stringBuilder = new StringBuilder(CONTAINER_SIZE);
        for (int index = 0; index < line.length(); index++) {
            stringBuilder.append(line.charAt(index));
            takenCount++;
            if (takenCount >= CONTAINER_SIZE) {
                fetched.add(getCharacter(stringBuilder));
                // Clean string builder without reallocation
                stringBuilder.delete(0, 3);
                takenCount = 0;
                // Skip divider index
                index++;
            }
        }
        return fetched;
    }

    private static Optional<Character> getCharacter(StringBuilder stringBuilder) {
        if (stringBuilder == null || stringBuilder.length() != CONTAINER_SIZE) {
            throw new IllegalArgumentException("Invalid string part: " + stringBuilder);
        }

        if (stringBuilder.chars().allMatch(i -> ((char) i) == ' ')) {
            return Optional.empty();
        }

        char characterToPossiblyReturn = stringBuilder.charAt(1);
        if (
                stringBuilder.charAt(0) == '[' &&
                Character.getType(characterToPossiblyReturn) == Character.UPPERCASE_LETTER &&
                stringBuilder.charAt(2) == ']'
        ) {
            return Optional.of(characterToPossiblyReturn);
        }

        throw new IllegalArgumentException("Invalid string part: " + stringBuilder);
    }
}
