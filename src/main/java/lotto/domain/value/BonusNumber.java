package lotto.domain.value;

import lotto.domain.error.LottoErrorCode;

public record BonusNumber(int value) {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    public BonusNumber {
        verifyOutOfLange(value);
    }

    private void verifyOutOfLange(int number) {
        if (number > MAX_NUMBER || number < MIN_NUMBER) {
            throw new IllegalArgumentException(LottoErrorCode.BONUS_NUMBER_OUT_OF_RANGE.getMessage());
        }
    }

}
