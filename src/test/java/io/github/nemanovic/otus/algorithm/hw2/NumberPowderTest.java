package io.github.nemanovic.otus.algorithm.hw2;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

import static io.github.nemanovic.otus.algorithm.TestHelper.METHOD_NAME;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;


public class NumberPowderTest {

    private static final String ROOT = "/hw2/3.Power/";
    private static final double EPSILON = 0.0000001d;

    // return { base, exponent, expectedResult }
    private static List<Object[]> testDataProvider() {
        return IntStream.rangeClosed(-1, 9).mapToObj(i -> ROOT + "test." + i) // test -1 heats cache
                .map(n -> {
                    Object[] data = new Object[3];
                    try (Scanner s = new Scanner(NumberPowderTest.class.getResourceAsStream(n + ".in"))) {
                        data[0] = Double.parseDouble(s.nextLine());
                        data[1] = Long.parseLong(s.nextLine());
                    }
                    try (Scanner s = new Scanner(NumberPowderTest.class.getResourceAsStream(n + ".out"))) {
                        data[2] = Double.parseDouble(s.nextLine());
                    }
                    return data;
                })
                .collect(toList());
    }

    // a*a*a*..*a
    // O(n)
    private double powViaIteration(double a, long N) {
        assert N >= 0;
        double result = 1;
        for (int i = 0; i < N; i++) {
            result *= a;
        }
        return result;
    }

    // a | a^2 | a^4 | a^8 |.. | a^k, k = 2^m | (a^k)*a*a*..*a = a^N
    // O(n) в худшем, O(log(n)) в лучшем
    private double powViaExponent2WithExtraMultiplication(double a, long N) {
        assert N >= 0;
        if (N == 0) {
            return 1;
        }

        double result = a;
        int exp = 1;

        //a^k, k = 2^m
        while ((exp *= 2) <= N) {
            result *= result;
        }

        for (int i = exp / 2; i < N; i++) {
            result *= a;
        }
        return result;
    }

    /* e.g N == 100: a^4*a^32*a^64
     Показатели степеней - разложение 100 по степеням двойки, т.е. по определению разряды двоичного представления
     100 в двоичной системе: 1100100 -> 0*2^0 + 0*2^1 + 1*2^2 + 0*2^3 + 0*2^4 + 1*2^5 + 1*2^6
     Чтобы получить двоичное представление, можно брать остатки от деления N на 2 каждом шаге (из определения)
     O(log(n))
    */
    private double powViaBinaryNotation(double a, long n) {
        assert n >= 0;

        double result = 1;
        while (n > 0) {
            if (n % 2 > 0) {
                result *= a;
            }
            a = a * a;
            n = n / 2;
        }
        return result;
    }

    @ParameterizedTest(name = METHOD_NAME)
    @MethodSource("testDataProvider")
    public void _testPowViaMathLib(double base, long exponent, double result) {
        assertEquals(result, Math.pow(base, exponent), EPSILON);
    }

    @ParameterizedTest(name = METHOD_NAME)
    @MethodSource("testDataProvider")
    public void testPowViaIteration(double base, long exponent, double result) {
        assertTimeoutPreemptively(
                Duration.ofSeconds(30),
                () -> assertEquals(result, powViaIteration(base, exponent), EPSILON)
        );
    }

    @ParameterizedTest(name = METHOD_NAME)
    @MethodSource("testDataProvider")
    public void testPowViaExponent2WithExtraMultiplication(double base, long exponent, double result) {
        assertTimeoutPreemptively(
                Duration.ofSeconds(30),
                () -> assertEquals(result, powViaExponent2WithExtraMultiplication(base, exponent), EPSILON)
        );
    }

    @ParameterizedTest(name = METHOD_NAME)
    @MethodSource("testDataProvider")
    public void testPowViaBinaryNotation(double base, long exponent, double result) {
        assertEquals(result, powViaBinaryNotation(base, exponent), EPSILON);
    }
}
