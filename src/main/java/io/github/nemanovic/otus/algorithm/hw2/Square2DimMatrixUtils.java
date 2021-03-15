package io.github.nemanovic.otus.algorithm.hw2;

import java.math.BigInteger;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;

//Работа с матрицами 2x2
public class Square2DimMatrixUtils {

    public static BigInteger[][] multiply(BigInteger[][] a, BigInteger[][] b) {
        assert a.length == 2 && b.length == 2;
        assert a[0].length == 2 && a[1].length == 2;
        assert b[0].length == 2 && b[1].length == 2;

        BigInteger[][] result = new BigInteger[2][2];
        result[0][0] = a[0][0].multiply(b[0][0]).add(a[0][1].multiply(b[1][0]));
        result[0][1] = a[0][0].multiply(b[0][1]).add(a[0][1].multiply(b[1][1]));
        result[1][0] = a[1][0].multiply(b[0][0]).add(a[1][1].multiply(b[1][0]));
        result[1][1] = a[1][0].multiply(b[0][1]).add(a[1][1].multiply(b[1][1]));
        return result;
    }

    public static BigInteger[][] pow(BigInteger[][] matrix, long n) {
        assert n >= 0;
        assert matrix.length == 2 && matrix[0].length == 2 && matrix[1].length == 2;

        BigInteger[][] result = new BigInteger[][]{
                new BigInteger[]{ONE, ZERO},
                new BigInteger[]{ZERO, ONE}
        };

        while (n > 0) {
            if (n % 2 > 0) {
                result = multiply(result, matrix);
            }
            matrix = multiply(matrix, matrix);
            n = n / 2;
        }

        return result;
    }
}
