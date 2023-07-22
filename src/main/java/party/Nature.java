package party;

import java.util.Random;

public enum Nature {
    HARDY, LONELY, ADAMANT, NAUGHTY, BRAVE,
    BOLD, DOCILE, IMPISH, LAX, RELAXED,
    MODEST, MILD, BASHFUL, RASH, QUIET,
    CALM, GENTLE, CAREFUL, QUIRKY, SASSY,
    TIMID, HASTY, JOLLY, NAIVE, SERIOUS;

    private static final Nature[] VALUES = values();
    private static final int SIZE = VALUES.length;
    private static final Random RANDOM = new Random();

    public static Nature getRandomNature()  {
        return VALUES[RANDOM.nextInt(SIZE)];
    }
}
