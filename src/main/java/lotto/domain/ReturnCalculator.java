package lotto.domain;


import java.util.Map;
import lotto.domain.lotto.Rank;

public class ReturnCalculator {
    public static double calculate(int amount, Map<Rank, Integer> result) {
        long sum = result.entrySet().stream()
                .mapToLong(e -> (long) e.getKey().getPrize() * e.getValue())
                .sum();

        if (amount == 0) {
            return 0.0;
        }
        return (double) sum / (amount * 1000) * 100;

    }

}
