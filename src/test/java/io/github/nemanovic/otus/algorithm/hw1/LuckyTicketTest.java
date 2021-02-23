package io.github.nemanovic.otus.algorithm.hw1;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LuckyTicketTest {
    private static final String ROOT = "/hw1/Tickets/";

    //Тесты 6-9 выполняются слишком медленно для этого алгоритма
    private static Stream<Object[]> fileProvider() {
        return IntStream.rangeClosed(0, 3).mapToObj(i -> ROOT + "test." + i)
                .map(n -> Stream.of(n + ".in", n + ".out")
                        .map(path -> {
                            try (Scanner s = new Scanner(LuckyTicketTest.class.getResourceAsStream(path))) {
                                return s.nextLine();
                            }
                        }).toArray());
    }

    @ParameterizedTest
    @MethodSource("fileProvider") // длина тикета - 2N
    public void testLuckyMethod(String in, String out) {
        assertEquals(Long.valueOf(out), new LuckyTicketsSolution(2*Integer.valueOf(in)).getLuckyTicketsCount());
    }

}
