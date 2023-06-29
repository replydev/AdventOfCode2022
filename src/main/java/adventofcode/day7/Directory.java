package adventofcode.day7;

import java.util.ArrayList;
import java.util.List;

public class Directory implements Sizeable {

    private final String name;
    private final List<Sizeable> directElements;

    public Directory(String name) {
        this.name = name;
        this.directElements = new ArrayList<>();
    }

    public void addElement(Sizeable sizeable) {
        directElements.add(sizeable);
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public int size() {
        return directElements
                .stream()
                .map(Sizeable::size)
                .reduce(Integer::sum)
                .orElse(0);
    }
}
