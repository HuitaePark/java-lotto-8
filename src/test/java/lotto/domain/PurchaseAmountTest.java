package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.domain.value.PurchaseAmount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PurchaseAmountTest {

    @DisplayName("구입 금액을 받아 1000으로 나누어 구매 횟수를 구한다.")
    @Test
    void divide_by_1000_to_get_the_number_of_purchases() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(15000);

        assertThat(purchaseAmount.getQuantity()).isEqualTo(15);
    }

    @DisplayName("구입 금액이 1000으로 나누어 떨어지지 않을경우 에러 발생")
    @Test
    void Error_if_the_purchase_amount_is_not_divisible_by_1000() {
        assertThatThrownBy(() -> new PurchaseAmount(151515))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구입 금액이 1000으로 나누어 떨어지지 않을경우 에러 발생")
    @Test
    void Error_if_the_purchase_amount_is_minus() {
        assertThatThrownBy(() -> new PurchaseAmount(-1000))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
