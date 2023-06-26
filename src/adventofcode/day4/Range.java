package adventofcode.day4;

public class Range {

    private final int start;
    private final int stop;

    public Range(String range) {
        if (range == null) {
            throw new IllegalArgumentException("Range should not be null");
        }
        String[] parts = range.split("-");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Malformed range: " + range);
        }

        try {
            this.start = Integer.parseInt(parts[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number: " + parts[0]);
        }

        try {
            this.stop = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number: " + parts[1]);
        }

        if (start > stop) {
            throw new IllegalArgumentException(String.format("Range start is greater than range stop: %d > %d", start, stop));
        }
    }

    public boolean contains(Range other) {
        return other.start >= this.start && other.stop <= this.stop;
    }
}
