package lotto.presentation.ui;

import java.util.List;
import lotto.application.dto.IssuedLottoDto;
import lotto.application.dto.WinningStaticsDto;

public class OutputView {
    public void printStartMessage() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void printPurchaseCount(int count) {
        System.out.printf("\n%d개를 구매했습니다.\n", count);
    }

    public void printErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }

    public void printIssuedTicket(List<IssuedLottoDto> issuedLottoDtos) {
        issuedLottoDtos.forEach(dto -> System.out.println(dto.numbers()));
    }

    public void entryMessage() {
        System.out.println("\n당첨 번호를 입력해 주세요.");
    }

    public void bonusMessage() {
        System.out.println("\n보너스 번호를 입력해 주세요.");
    }

    public void printStatics(WinningStaticsDto dto) {
        System.out.println("\n당첨 통계\n---");
        System.out.println(dto.message());
        System.out.printf("총 수익률은 %.1f%%입니다.", dto.yield());
    }
}
