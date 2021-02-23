package io.github.nemanovic.otus.algorithm.hw1;

public class LuckyTicketsSolution {
    private final int ticketDimension;

    public LuckyTicketsSolution(int ticketDimension) {
        this.ticketDimension = ticketDimension;
    }

    public long getLuckyTicketsCount() {
        return getLuckyTicketsCount(0, 0, 0, 0);
    }

    private long getLuckyTicketsCount(long count, int n, int leftSum, int rightSum) {
        if (this.ticketDimension == n) {
            if (leftSum == rightSum) {
                count++;
            }
            return count;
        }

        for (int digit = 0; digit <= 9; digit++) {
            if (n < this.ticketDimension / 2) {
                count = getLuckyTicketsCount(count, n + 1, leftSum + digit, rightSum);
            } else {
                count = getLuckyTicketsCount(count, n + 1, leftSum, rightSum + digit);
            }
        }
        return count;
    }
}
