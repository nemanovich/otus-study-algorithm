package io.github.nemanovic.otus.algorithm.hw1;

public class LuckyTicketsSolution {
    private final int ticketDimension;
    long count = 0;

    public LuckyTicketsSolution(int ticketDimension) {
        this.ticketDimension = ticketDimension;
    }

    public long getLuckyTicketsCount() {
        getLuckyTicketsCount(0, 0, 0);
        return this.count;
    }

    private void getLuckyTicketsCount(int n, int leftSum, int rightSum) {
        if (this.ticketDimension == n) {
            if (leftSum == rightSum) {
                this.count++;
            }
            return;
        }

        for (int digit = 0; digit <= 9; digit++) {
            if (n < this.ticketDimension / 2) {
                getLuckyTicketsCount(n + 1, leftSum + digit, rightSum);
            } else {
                getLuckyTicketsCount(n + 1, leftSum, rightSum + digit);
            }
        }
    }
}
