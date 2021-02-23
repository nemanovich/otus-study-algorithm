package io.github.nemanovic.otus.algorithm.hw1;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.opentest4j.AssertionFailedError;

import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringLengthTest {
    private static final String ROOT = "/String/";

    private static Stream<Object[]> fileProvider() {
        return IntStream.rangeClosed(0, 3).mapToObj(i -> ROOT + "test." + i)
                .map(n -> Stream.of(n + ".in", n + ".out").map(path -> {
                    try (Scanner s = new Scanner(StringLengthTest.class.getResourceAsStream(path))) {
                        return s.nextLine();
                    }
                }).toArray());
    }

    private static Stream<Object[]> fileProviderWithError() {
        return Stream.of(ROOT + "test.4")
                .map(n -> Stream.of(n + ".in", n + ".out").map(path -> {
                    try (Scanner s = new Scanner(StringLengthTest.class.getResourceAsStream(path))) {
                        return s.nextLine();
                    }
                }).toArray());
    }

    @ParameterizedTest
    @MethodSource("fileProvider")
    public void testComparing(String in, String out) {
        assertEquals(out, String.valueOf(in.length()));
    }

    @ParameterizedTest
    @MethodSource("fileProviderWithError")
    public void testComparingError(String in, String out) {
        assertThrows(AssertionFailedError.class, () -> testComparing(in, out));
    }
}
