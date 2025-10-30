package lotto.domain;

public record BonusNumber(int number) {

    public BonusNumber {
        verifyOutOfLange(number);
    }

    private void verifyOutOfLange(int number) {
        if (number > 45 || number < 1) {
            throw new IllegalArgumentException("보너스 넘버는 1에서 45 사이여야 합니다.");
        }
    }

}
