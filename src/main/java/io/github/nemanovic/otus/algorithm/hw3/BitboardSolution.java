package io.github.nemanovic.otus.algorithm.hw3;

import static io.github.nemanovic.otus.algorithm.hw3.BitboardMasks.EXCLUDE_A;
import static io.github.nemanovic.otus.algorithm.hw3.BitboardMasks.EXCLUDE_B;
import static io.github.nemanovic.otus.algorithm.hw3.BitboardMasks.EXCLUDE_G;
import static io.github.nemanovic.otus.algorithm.hw3.BitboardMasks.EXCLUDE_H;

// Шахматная битовая доска https://habr.com/ru/company/otus/blog/476510/
public class BitboardSolution {
    public static long findKingMoves(int positionIndex) {
        long p = Long.rotateLeft(1, positionIndex);
        return ((p & EXCLUDE_A) << 7) | (p << 8) | ((p & EXCLUDE_H) << 9)  // "верхние" ходы
                | ((p & EXCLUDE_A) >>> 1) | ((p & EXCLUDE_H) << 1)  // боковые
                | ((p & EXCLUDE_A) >>> 9) | (p >>> 8) | ((p & EXCLUDE_H) >>> 7); // нижние
    }

    public static long findKnightMoves(int positionIndex) {
        long p = Long.rotateLeft(1, positionIndex);
        return ((p & EXCLUDE_A) << 15) | ((p & EXCLUDE_H) << 17) |
                ((p & EXCLUDE_A & EXCLUDE_B) << 6) | ((p & EXCLUDE_G & EXCLUDE_H) << 10) |
                ((p & EXCLUDE_A & EXCLUDE_B) >>> 10) | ((p & EXCLUDE_G & EXCLUDE_H) >>> 6) |
                ((p & EXCLUDE_A) >>> 17) | ((p & EXCLUDE_H) >>> 15);
    }

    public static long findRookMoves(int positionIndex) {
        long p = Long.rotateLeft(1, positionIndex);
        long position = 0;
        long excludeLeftColumns = BitboardMasks.FILLED;
        long excludeRightColumns = BitboardMasks.FILLED;
        for (int i = 0; i < 8; i++) {
            excludeLeftColumns &= BitboardMasks.getExcludeColumn(i);
            excludeRightColumns &= BitboardMasks.getExcludeColumn(7 - i);
            position |= (p << (i + 1) * 8) | (p >>> (i + 1) * 8) | // вверх/вниз по доске без ограничений
                    ((p & excludeLeftColumns) >>> i + 1) | //пошагово смещаем влево по доске, расширяя "запретную зону"
                    ((p & excludeRightColumns) << i + 1); // аналогично вправо
        }
        return position - p; // вместо проверки ограничений вертикального смещения исключаем начальную точку.
    }

    // аналогично ладье, но надо проверять ограничения в любом направлении
    public static long findBishopMoves(int positionIndex) {
        long p = Long.rotateLeft(1, positionIndex);
        long position = 0;
        long excludeLeftColumns = BitboardMasks.FILLED;
        long excludeRightColumns = BitboardMasks.FILLED;
        for (int i = 0; i < 8; i++) {
            excludeLeftColumns &= BitboardMasks.getExcludeColumn(i);
            excludeRightColumns &= BitboardMasks.getExcludeColumn(7 - i);
            position |= ((p & excludeLeftColumns) << (i + 1) * 7) |
                    ((p & excludeRightColumns) << (i + 1) * 9) |
                    ((p & excludeRightColumns) >>> (i + 1) * 7) |
                    ((p & excludeLeftColumns) >>> (i + 1) * 9);
        }
        return position;
    }

    public static long findQueenMoves(int positionIndex) {
        return findBishopMoves(positionIndex) | findRookMoves(positionIndex);
    }
}
