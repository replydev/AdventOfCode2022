package adventofcode.day6;

public class Day6 {

    public static int packet(String sequence) {
        return new PacketProcessor(sequence).characterProcessed();
    }

}
