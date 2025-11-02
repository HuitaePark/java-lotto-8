package lotto.domain;


import java.util.Map;
import lotto.domain.lotto.Rank;

public class ReturnCalculator {
    public static double calculate(int amount, Map<Rank, Integer> result) {
        int money = amount * 1000;
        int sum = result.entrySet().stream()
                .mapToInt(rank -> rank.getKey().getPrize() * rank.getValue())
                .sum();

        if (money == 0) {
            return 0.0;
        }

        return (double) (sum - money) / money * 100;
    }

}
