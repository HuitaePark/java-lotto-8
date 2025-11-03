package lotto.domain.util;

import java.util.Map;
import lotto.domain.lotto.Rank;

public final class ReturnCalculator {
    private static final int LOTTO_PRICE = 1000;
    private static final int PERCENT = 100;

    private ReturnCalculator() {
    }

    public static double calculate(int amount, Map<Rank, Integer> result) {
        long sum = result.entrySet().stream()
                .mapToLong(e -> (long) e.getKey().getPrize() * e.getValue())
                .sum();

        if (amount == 0) {
            return 0.0;
        }
        return (double) sum / (amount * LOTTO_PRICE) * PERCENT;
    }
}
