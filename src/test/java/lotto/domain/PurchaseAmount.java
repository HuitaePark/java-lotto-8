package lotto.domain;

public class PurchaseAmount {
    private static final int LOTTO_PRICE = 1_000;
    private final int money;

    public PurchaseAmount(int money) {
        this.money = money;
    }

    public int getQuantity() {
        return money / LOTTO_PRICE;
    }
}
