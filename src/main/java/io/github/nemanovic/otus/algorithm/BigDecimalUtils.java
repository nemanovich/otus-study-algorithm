package io.github.nemanovic.otus.algorithm;

import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_HALF_UP;

public class BigDecimalUtils {
    //для java 8
    public static BigDecimal sqrt(BigDecimal A) {
        BigDecimal x0 = BigDecimal.ZERO;
        BigDecimal x1 = BigDecimal.valueOf(Math.sqrt(A.doubleValue()));
        while (!x0.equals(x1)) {
            x0 = x1;
            x1 = A.divide(x0, A.scale(), ROUND_HALF_UP);
            x1 = x1.add(x0);
            x1 = x1.divide(BigDecimal.valueOf(2), A.scale(), ROUND_HALF_UP);

        }
        return x1;
    }
}
