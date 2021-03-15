package io.github.nemanovic.otus.algorithm.hw3;

// https://gekomad.github.io/Cinnamon/BitboardCalculator/
public class BitboardMasks {

    public static final long FILLED = Long.parseUnsignedLong("18446744073709551615");

    private static final long[] EXCLUDE_COLUMNS = {
            Long.parseUnsignedLong("18374403900871474942"),
            Long.parseUnsignedLong("18302063728033398269"),
            Long.parseUnsignedLong("18157383382357244923"),
            Long.parseUnsignedLong("17868022691004938231"),
            Long.parseUnsignedLong("17289301308300324847"),
            Long.parseUnsignedLong("16131858542891098079"),
            Long.parseUnsignedLong("13816973012072644543"),
            Long.parseUnsignedLong("9187201950435737471")
    };


    public static final long EXCLUDE_A = EXCLUDE_COLUMNS[0];
    public static final long EXCLUDE_B = EXCLUDE_COLUMNS[1];
    public static final long EXCLUDE_G = EXCLUDE_COLUMNS[6];
    public static final long EXCLUDE_H = EXCLUDE_COLUMNS[7];

    public static long getExcludeColumn(int i) {
        assert i >= 0 && i < 8;
        return EXCLUDE_COLUMNS[i];
    }
}
