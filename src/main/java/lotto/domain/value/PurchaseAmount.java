package lotto.domain.value;

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
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1000단위로 입력해야 합니다.");
        }
    }

    private void verifyMinus(int money) {
        if (money <= 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 0원 미만이 될 수 없습니다.");
        }
    }
}
