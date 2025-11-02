package lotto.presentation.ui;

import java.util.List;
import lotto.application.dto.IssuedLottoDto;

public class OutputView {
    public void printStartMessage() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void printPurchaseCount(int count) {
        System.out.printf("%d개를 구매했습니다.", count);
    }

    public void printErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }

    public void printIssuedTicket(List<IssuedLottoDto> issuedLottoDtos) {
        issuedLottoDtos.forEach(dto -> System.out.println(dto.numbers()));
    }
}
