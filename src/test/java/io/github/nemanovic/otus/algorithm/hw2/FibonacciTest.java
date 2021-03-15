package io.github.nemanovic.otus.algorithm.hw2;


import io.github.nemanovic.otus.algorithm.BigDecimalUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

import static io.github.nemanovic.otus.algorithm.TestHelper.METHOD_NAME;
import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;
import static java.math.RoundingMode.HALF_EVEN;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;


public class FibonacciTest {

    private static List<Object[]> testDataProvider() {
        return IntStream.rangeClosed(0, 11).mapToObj(i -> "/hw2/4.Fibo/test." + i)
                .map(n -> {
                    Object[] data = new Object[2];
                    try (Scanner s = new Scanner(FibonacciTest.class.getResourceAsStream(n + ".in"))) {
                        data[0] = s.nextInt();
                    }
                    try (Scanner s = new Scanner(FibonacciTest.class.getResourceAsStream(n + ".out"))) {
                        data[1] = s.nextBigInteger();
                    }
                    return data;
                })
                .collect(toList());
    }

    // O(2^n) без мемоизации
    private BigInteger findFiboViaRecurrent(int n) {
        if (n <= 1) {
            return BigInteger.valueOf(n);
        }
        return findFiboViaRecurrent(n - 2).add(findFiboViaRecurrent(n - 1));
    }

    // по формуле Бине
    // O(log(n)) операций без учета длинной арифматики. при N > 10000 накапливается погрешность.
    private BigInteger findFiboViaGoldenRatio(int n) {
        if (n <= 1) {
            return BigInteger.valueOf(n);
        }

        BigDecimal sqrt5 = BigDecimalUtils.sqrt(BigDecimal.valueOf(5).setScale(999, HALF_EVEN));
        BigDecimal goldenRatio = BigDecimal.ONE.add(sqrt5).divide(BigDecimal.valueOf(2), HALF_EVEN);
        return goldenRatio.pow(n)
                .divide(sqrt5, HALF_EVEN)
                .add(BigDecimal.valueOf(0.5))
                .setScale(0, RoundingMode.DOWN)
                .toBigInteger();
    }

    // Матрица перехода q находится из уравнения ((f2, f1), (f1, f0)) * X = ((f3, f2), (f2, f1))
    // O(log(n)) операций без учета длинной арифматики.
    private BigInteger findFiboViaMatrixMultiplication(int n) {
        if (n <= 1) {
            return BigInteger.valueOf(n);
        }

        BigInteger[][] q = new BigInteger[][]{
                new BigInteger[]{ONE, ONE},
                new BigInteger[]{ONE, ZERO}
        };
        return Square2DimMatrixUtils.pow(q, n - 1)[0][0];
    }

    @ParameterizedTest(name = METHOD_NAME)
    @MethodSource("testDataProvider")
    public void testFindFiboViaRecurrent(int n, BigInteger result) {
        assertTimeoutPreemptively(Duration.ofSeconds(60),
                () -> assertEquals(result, findFiboViaRecurrent(n))
        );
    }

    @ParameterizedTest(name = METHOD_NAME)
    @MethodSource("testDataProvider")
    public void testFindFiboViaGoldenRatio(int n, BigInteger result) {
        assertTimeoutPreemptively(Duration.ofSeconds(60),
                () -> assertEquals(result, findFiboViaGoldenRatio(n))
        );
    }

    @ParameterizedTest(name = METHOD_NAME)
    @MethodSource("testDataProvider")
    public void testFindFiboViaMatrixMultiplication(int n, BigInteger result) {
        assertTimeoutPreemptively(Duration.ofSeconds(60),
                () -> assertEquals(result, findFiboViaMatrixMultiplication(n))
        );
    }
}
