package io.github.nemanovic.otus.algorithm.hw3;


import io.github.nemanovic.otus.algorithm.TestHelper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

import static io.github.nemanovic.otus.algorithm.TestHelper.METHOD_NAME;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BitboardSolutionTest {
    private static List<Object[]> testKingProvider() {
        return IntStream.rangeClosed(0, 9)
                .mapToObj(i -> "/hw3/1.Bitboard.King/test." + i)
                .map(getBitboardTestMapping()).collect(toList());
    }

    private static List<Object[]> testKnightProvider() {
        return IntStream.rangeClosed(0, 9)
                .mapToObj(i -> "/hw3/2.Bitboard.Knight/test." + i)
                .map(getBitboardTestMapping()).collect(toList());
    }

    private static List<Object[]> testRookProvider() {
        return IntStream.rangeClosed(0, 9)
                .mapToObj(i -> "/hw3/3.Bitboard.Rook/test." + i)
                .map(getBitboardTestMapping())
                .collect(toList());
    }

    private static List<Object[]> testBishopProvider() {
        return IntStream.rangeClosed(0, 9)
                .mapToObj(i -> "/hw3/4.Bitboard.Bishop/test." + i)
                .map(getBitboardTestMapping())
                .collect(toList());
    }

    private static List<Object[]> testQueenProvider() {
        return IntStream.rangeClosed(0, 9)
                .mapToObj(i -> "/hw3/5.Bitboard.Queen/test." + i)
                .map(getBitboardTestMapping())
                .collect(toList());
    }

    private static Function<String, Object[]> getBitboardTestMapping() {
        return n -> TestHelper.concat(
                TestHelper.readResource(n + ".in", s -> new Object[]{
                        s.nextInt()
                }),
                TestHelper.readResource(n + ".out", s ->
                        new Object[]{
                                Integer.parseInt(s.nextLine()),
                                Long.parseUnsignedLong(s.nextLine())
                        })
        );
    }

    @ParameterizedTest(name = METHOD_NAME)
    @MethodSource("testKingProvider")
    public void testKingMoves(int positionIndex, int expectedCount, long expectedMap) {
        long actualMap = BitboardSolution.findKingMoves(positionIndex);
        assertEquals(expectedMap, actualMap);
        assertEquals(expectedCount, Long.bitCount(actualMap));
    }

    @ParameterizedTest(name = METHOD_NAME)
    @MethodSource("testKnightProvider")
    public void testKnightMoves(int positionIndex, int expectedCount, long expectedMap) {
        long actualMap = BitboardSolution.findKnightMoves(positionIndex);
        assertEquals(expectedMap, actualMap);
        assertEquals(expectedCount, Long.bitCount(actualMap));
    }

    @ParameterizedTest(name = METHOD_NAME)
    @MethodSource("testRookProvider")
    public void testRookMoves(int positionIndex, int expectedCount, long expectedMap) {
        long actualMap = BitboardSolution.findRookMoves(positionIndex);
        assertEquals(expectedMap, actualMap);
        assertEquals(expectedCount, Long.bitCount(actualMap));
    }

    @ParameterizedTest(name = METHOD_NAME)
    @MethodSource("testBishopProvider")
    public void testBishopMoves(int positionIndex, int expectedCount, long expectedMap) {
        long actualMap = BitboardSolution.findBishopMoves(positionIndex);
        assertEquals(expectedMap, actualMap);
        assertEquals(expectedCount, Long.bitCount(actualMap));
    }

    @ParameterizedTest(name = METHOD_NAME)
    @MethodSource("testQueenProvider")
    public void testQueenMoves(int positionIndex, int expectedCount, long expectedMap) {
        long actualMap = BitboardSolution.findQueenMoves(positionIndex);
        assertEquals(expectedMap, actualMap);
        assertEquals(expectedCount, Long.bitCount(actualMap));
    }
}
