package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.EnumMap;
import lotto.domain.lotto.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ReturnCalculatorTest {

    @DisplayName("구매금액과 일치한 로또의 금액 합을 받아 수익률을 구한다.")
    @Test
    void return_success_Test() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(1000);
        EnumMap<Rank, Integer> result = Rank.initialize();
        result.put(Rank.FIFTH_PLACE, 1);

        assertThat(ReturnCalculator.calculate(purchaseAmount, result)).isEqualTo(400.0);
    }

    @DisplayName("당첨된 로또 금액의 합이 적다면 손실이 발생한다.")
    @Test
    void return_minus_Test() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(10000);
        EnumMap<Rank, Integer> result = Rank.initialize();
        result.put(Rank.FIFTH_PLACE, 1);

        assertThat(ReturnCalculator.calculate(purchaseAmount, result)).isEqualTo(-50.0);
    }
}
