package adventofcode.day2;

import java.util.Map;

public enum GameElement implements Comparable<GameElement> {
    ROCK(1),
    PAPER(2),
    SCISSORS(3);

    private static final Map<GameElement, GameElement> winsMatrix = Map.of(ROCK, SCISSORS, PAPER, ROCK, SCISSORS, PAPER);
    private static final int DRAW_POINTS = 3;
    private static final int LOSE_POINTS = 0;
    private static final int WIN_POINTS = 6;
    private final int elementScore;

    public int getElementScore() {
        return elementScore;
    }

    GameElement(int elementScore) {
        this.elementScore = elementScore;
    }

    public static int calculateMatch(GameElement fromPlayer, GameElement fromOpponent) {
        if (winsMatrix.get(fromPlayer) == fromOpponent) {
            return WIN_POINTS;
        } else if (winsMatrix.get(fromOpponent) == fromPlayer) {
            return LOSE_POINTS;
        }
        return DRAW_POINTS;
    }

}
