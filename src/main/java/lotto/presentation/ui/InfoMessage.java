package lotto.presentation.ui;

public enum InfoMessage {
    ASK_AMOUNT("구입금액을 입력해 주세요."),
    PURCHASED_COUNT("\n%d개를 구매했습니다.\n"),
    ENTER_WINNING("\n당첨 번호를 입력해 주세요."),
    ENTER_BONUS("\n보너스 번호를 입력해 주세요."),
    STATISTICS_HEADER("\n당첨 통계\n---"),
    YIELD("총 수익률은 %.1f%%입니다.");

    private final String message;

    InfoMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
