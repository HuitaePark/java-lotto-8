package lotto.domain.error;

public enum LottoErrorCode {
    INVALID_SORT("[ERROR] 로또 번호는 오름차순으로 정렬되어야 합니다."),

    INVALID_SIZE("[ERROR] 로또 번호는 6개여야 합니다."),
    DUPLICATE_NUMBER("[ERROR] 로또 번호는 중복될 수 없습니다."),
    OUT_OF_RANGE("[ERROR] 로또 번호는 1보다 작거나 45보다 클 수 없습니다."),

    DUPLICATE_WINNER("[ERROR] 보너스 번호는 로또번호와 중복될수 없습니다."),

    INVALID_AMOUNT_REMAINDER("[ERROR] 구입 금액은 1000단위로 입력해야 합니다."),
    INVALID_AMOUNT_MINUS("[ERROR] 구입 금액은 0원 미만이 될 수 없습니다."),

    BONUS_NUMBER_OUT_OF_RANGE("[ERROR] 보너스 넘버는 1에서 45 사이여야 합니다.");

    private final String message;

    LottoErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
