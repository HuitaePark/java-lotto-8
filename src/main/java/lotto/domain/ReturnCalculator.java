package lotto.domain;


import java.util.EnumMap;
import lotto.domain.lotto.Rank;
import lotto.domain.value.PurchaseAmount;

public class ReturnCalculator {
    public static double calculate(PurchaseAmount purchaseAmount, EnumMap<Rank, Integer> result) {
        int amount = purchaseAmount.money();
        int sum = result.entrySet().stream()
                .mapToInt(rank -> rank.getKey().getPrize() * rank.getValue())
                .sum();

        return (double) (sum - amount) / amount * 100;
    }

}
