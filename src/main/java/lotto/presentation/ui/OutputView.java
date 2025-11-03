package lotto.presentation.ui;

import java.util.List;
import lotto.application.dto.IssuedLottoDto;
import lotto.application.dto.WinningStaticsDto;

public class OutputView {
    public void printStartMessage() {
        System.out.println(InfoMessage.ASK_AMOUNT.getMessage());
    }

    public void printPurchaseCount(int count) {
        System.out.printf(InfoMessage.PURCHASED_COUNT.getMessage(), count);
    }

    public void printErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }

    public void printIssuedTicket(List<IssuedLottoDto> issuedLottoDtos) {
        issuedLottoDtos.forEach(dto -> System.out.println(dto.numbers()));
    }

    public void entryMessage() {
        System.out.println(InfoMessage.ENTER_WINNING.getMessage());
    }

    public void bonusMessage() {
        System.out.println(InfoMessage.ENTER_BONUS.getMessage());
    }

    public void printStatics(WinningStaticsDto dto) {
        System.out.println(InfoMessage.STATISTICS_HEADER.getMessage());
        System.out.println(dto.message());
        System.out.printf(InfoMessage.YIELD.getMessage(), dto.yield());
    }
}
