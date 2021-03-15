package io.github.nemanovic.otus.algorithm;

import io.github.nemanovic.otus.algorithm.hw3.BitboardSolutionTest;
import org.junit.jupiter.params.ParameterizedTest;

import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Stream;


public class TestHelper {
    public static final String METHOD_NAME = ParameterizedTest.DISPLAY_NAME_PLACEHOLDER +
            ParameterizedTest.DEFAULT_DISPLAY_NAME;

    public static Object[] readResource(String path, final Function<Scanner, Object[]> readFunction) {
        try (Scanner s = new Scanner(BitboardSolutionTest.class.getResourceAsStream(path))) {
            return readFunction.apply(s);
        }
    }

    public static Object[] concat(Object[] a, Object[] b) {
        return Stream.of(a, b).flatMap(Stream::of).toArray();
    }
}
