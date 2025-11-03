package lotto.domain.value;

import lotto.domain.error.LottoErrorCode;

public record BonusNumber(int value) {

    public BonusNumber {
        verifyOutOfLange(value);
    }

    private void verifyOutOfLange(int number) {
        if (number > 45 || number < 1) {
            throw new IllegalArgumentException(LottoErrorCode.BONUS_NUMBER_OUT_OF_RANGE.getMessage());
        }
    }

}
