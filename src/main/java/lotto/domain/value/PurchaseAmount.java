package lotto.domain.value;

import lotto.domain.error.LottoErrorCode;

public record PurchaseAmount(int money) {
    private static final int LOTTO_PRICE = 1_000;

    public PurchaseAmount {
        verifyRemainder(money);
        verifyMinus(money);
    }

    public int getQuantity() {
        return money / LOTTO_PRICE;
    }

    private void verifyRemainder(int money) {
        if (money % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(LottoErrorCode.INVALID_AMOUNT_REMAINDER.getMessage());
        }
    }

    private void verifyMinus(int money) {
        if (money <= 0) {
            throw new IllegalArgumentException(LottoErrorCode.INVALID_AMOUNT_MINUS.getMessage());
        }
    }
}
