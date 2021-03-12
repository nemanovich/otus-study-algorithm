package io.github.nemanovic.otus.algorithm.hw2;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static io.github.nemanovic.otus.algorithm.TestHelper.METHOD_NAME;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

// Вычисление количества простых чисел от 1 до N
public class PrimeNumbersAmountTest {

    private static List<Object[]> testDataProvider() {
        return IntStream.rangeClosed(0, 14).mapToObj(i -> "/hw2/5.Primes/test." + i)
                .map(n -> Stream.of(n + ".in", n + ".out")
                        .map(path -> {
                            try (Scanner s = new Scanner(PrimeNumbersAmountTest.class.getResourceAsStream(path))) {
                                return s.nextInt();
                            }
                        }).toArray())
                .collect(toList());
    }


    /*
     Решето Эратосфена за O(log(log(n)))
      Итеративно вычеркиваем все числа k*p (k > 2), где в итоге все невычеркнутые p - простые. Цикл вычеркивания
      начинаем с квадратов, т.к. в ряду 2p, 3p .. np, n < p, все числа уже вычеркнуты (2..p - простые делители)
     */

    private long findPrimeNumbersViaEratosthenesSieve(int n) {
        boolean[] a = new boolean[n + 1];
        int count = 0;

        for (int i = 2; i <= n; i++) {
            if (!a[i]) {
                count++;
                //Вариант с (int j = i*i; i <= n; i++) потребует хаков проверки на переполнение int
                for (int j = 2; Math.multiplyExact(i, j) <= n; j++) {
                    a[Math.multiplyExact(i, j)] = true;
                }
            }
        }
        return count;
    }

    //Итеративный метод с мемоизацией (запоминаем ранее найденные простые числа)
    private long findPrimeNumbersViaIterationWithMemo(int n) {
        int[] primes = new int[n];
        int count = 0;
        primes[0] = 2;

        for (int i = 2; i <= n; i++) {
            if (isPrime(i, primes)) {
                count++;
                primes[count] = i;
            }
        }
        return count;
    }

    private static boolean isPrime(int x, int[] primes) {
        double sqrtX = Math.sqrt(x);
        for (int i = 0; primes[i] <= sqrtX; i++) {
            if (x % primes[i] == 0) {
                return false;
            }
        }
        return true;
    }

    //Итеративный метод
    private long findPrimeNumbersViaIteration(int n) {
        int count = 0;
        for (int i = 2; i <= n; i++) {
            if (isPrime(i)) {
                count++;
            }
        }
        return count;
    }

    private static boolean isPrime(int x) {
        double sqrtX = Math.sqrt(x);
        for (int i = 2; i <= sqrtX; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }


    @ParameterizedTest(name = METHOD_NAME)
    @MethodSource("testDataProvider")
    public void testFindPrimeNumbersViaIteration(int n, int result) {
        assertTimeoutPreemptively(
                Duration.ofSeconds(60),
                () -> assertEquals(result, findPrimeNumbersViaIteration(n))
        );
    }

    @ParameterizedTest(name = METHOD_NAME)
    @MethodSource("testDataProvider")
    public void testFindPrimeNumbersViaIterationWithMemo(int n, int result) {
        assertTimeoutPreemptively(Duration.ofSeconds(60),
                () -> assertEquals(result, findPrimeNumbersViaIterationWithMemo(n))
        );
    }

    @ParameterizedTest(name = METHOD_NAME)
    @MethodSource("testDataProvider")
    public void testFindPrimeNumbersViaEratosthenesSieve(int n, int result) {
        assertTimeoutPreemptively(Duration.ofSeconds(60),
                () -> assertEquals(result, findPrimeNumbersViaEratosthenesSieve(n))
        );
    }

}
