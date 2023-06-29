package adventofcode.day6;

import java.util.HashSet;
import java.util.Set;

public class PacketProcessor {

    private static final int MARKER_LENGTH = 4;
    private final Set<Character> hashSet = new HashSet<>(MARKER_LENGTH);
    private final String dataStream;

    public PacketProcessor(String dataStream) {
        if (dataStream == null) {
            throw new IllegalArgumentException("Data Stream should not be null");
        }

        if (dataStream.length() < MARKER_LENGTH) {
            throw new IllegalArgumentException("Data Stream is too short");
        }

        this.dataStream = dataStream;
    }

    public int characterProcessed() {
        final char[] elaborating = new char[MARKER_LENGTH];
        for (int i = 0; i < MARKER_LENGTH; i++) {
            elaborating[i] = dataStream.charAt(i);
        }

        short oldestCharIndex = 0;

        for (int i = 4; i < dataStream.length(); i++) {
            if (isUnique(elaborating)) {
                return i;
            }

            // Replace the oldest cell with new element
            // This prevents shifting because it's expensive
            elaborating[oldestCharIndex] = dataStream.charAt(i);
            if (++oldestCharIndex == MARKER_LENGTH) {
                oldestCharIndex = 0;
            }
        }

        return -1;
    }

    private boolean isUnique(char[] elaborating) {
        for (char c : elaborating) {
            hashSet.add(c);
        }
        try {
            return hashSet.size() == MARKER_LENGTH;
        } finally {
            hashSet.clear();
        }
    }
}
