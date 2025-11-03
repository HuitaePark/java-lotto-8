package lotto.application.error;

public enum ParsingErrorCode {
    INVALID_NUMBER_INPUT("[ERROR] 구입금액은 숫자로 입력해야 합니다."),
    INVALID_WINNER_INPUT("[ERROR] 숫자 6개를 쉼표로 구분해 입력해야 합니다. 예: 1,2,3,4,5,6"),
    INVALID_OUT_OF_NUMBER_RANGE("[ERROR] 너무 많은 수는 입력할 수 없습니다.");

    private final String message;

    ParsingErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
