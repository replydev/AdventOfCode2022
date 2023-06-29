package adventofcode.day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Parser {

    private static final int CD_COMMAND_PARTS = 3;
    private static final int LS_LINE_PARTS = 2;
    private final Map<String, Directory> directoryMap;
    private final LinkedList<String> currentDir;
    private final String DIRECTORY_DELIMITER = "/";

    public Parser(Path path) throws IOException {
        directoryMap = new HashMap<>(Collections.singletonMap(DIRECTORY_DELIMITER, new Directory(DIRECTORY_DELIMITER)));
        currentDir = new LinkedList<>();

        List<String> lines = Files.readAllLines(path);

        for (String line : lines) {
            if (line.startsWith("$ cd")) {
                parseCd(line);
            } else if (!line.startsWith("$ ls")) {
                parseLsElement(line);
            }
        }
    }

    public int calculateSizeMost10000() {
        return directoryMap.values()
                .stream()
                .map(Directory::size)
                .filter(s -> s <= 100000)
                .reduce(Integer::sum)
                .orElse(0);
    }

    private void parseLsElement(String line) {
        if (line == null) {
            throw new IllegalArgumentException("Line should not be null");
        }

        String[] parts = line.split(" ");

        if (parts.length != LS_LINE_PARTS) {
            throw new IllegalArgumentException("Invalid line");
        }

        String currentDirString = joinedCurrentDir();
        Directory currentDirectory = directoryMap.get(currentDirString);
        if (currentDirectory == null) {
            throw new IllegalStateException("Directory not found: " + currentDirString);
        }

        Sizeable sizeable;
        if (parts[0].equals("dir")) {
            // Add the new dir to the current dir and to the dirs map
            Directory directory = new Directory(parts[1]);
            directoryMap.putIfAbsent(getDirectoryKey(parts[1]), directory);
            sizeable = directory;
        } else {
            int size = Integer.parseInt(parts[0]);
            String name = parts[1];
            sizeable = new File(name, size);
        }

        currentDirectory.addElement(sizeable);
    }

    private String getDirectoryKey(String directoryName) {
        return inRoot() ?
                joinedCurrentDir() + directoryName :
                joinedCurrentDir() + DIRECTORY_DELIMITER + directoryName;
    }

    private void parseCd(String cdCommand) {
        if (cdCommand == null) {
            throw new IllegalArgumentException("Command cannot be null");
        }

        String[] parts = cdCommand.split(" ");

        if (parts.length != CD_COMMAND_PARTS) {
            throw new IllegalArgumentException("Invalid cd command");
        }

        String dirName = parts[2];
        switch (dirName) {
            case ".." -> currentDir.pop();
            case DIRECTORY_DELIMITER -> {
                if (!inRoot()) {
                    currentDir.clear();
                    currentDir.add(dirName);
                }
            }
            default -> currentDir.add(dirName);
        }
    }

    private boolean inRoot() {
        return currentDir.size() == 0;
    }

    private String joinedCurrentDir() {
        if (inRoot()) {
            return DIRECTORY_DELIMITER;
        }
        return DIRECTORY_DELIMITER + String.join(DIRECTORY_DELIMITER, currentDir);
    }
}
