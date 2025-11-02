package lotto.presentation.controller;

import lotto.application.dto.WinningStaticsDto;
import lotto.application.service.LottoService;
import lotto.domain.lotto.WinningLotto;
import lotto.domain.value.BonusNumber;
import lotto.presentation.ui.InputView;
import lotto.presentation.ui.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public LottoController(InputView inputView, OutputView outputView, LottoService lottoService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
    }

    public void run() {
        readInputPurchaseAmount();
        printIssuedTicket();
        readInputWinningNumber();

        inputView.closeConsole();
    }

    private void readInputPurchaseAmount() {
        while (true) {
            try {
                outputView.printStartMessage();
                String input = inputView.inputText();
                int count = lottoService.getPurchaseCount(input);

                outputView.printPurchaseCount(count);
                return;
            } catch (IllegalArgumentException exception) {
                outputView.printErrorMessage(exception);
            }
        }
    }

    private void printIssuedTicket() {
        outputView.printIssuedTicket(lottoService.getIssuedTicket());
    }

    private void readInputWinningNumber() {
        WinningStaticsDto dto = lottoService.processWinningNumbers(readWinningNumbers(), readBonusNumber());
        outputView.printStatics(dto);
    }

    private WinningLotto readWinningNumbers() {
        while (true) {
            try {
                outputView.entryMessage();
                String input = inputView.inputText();
                return lottoService.getWinningNumbers(input);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private BonusNumber readBonusNumber() {
        while (true) {
            try {
                outputView.bonusMessage();
                String input = inputView.inputText();
                return lottoService.getBonusNumbers(input);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }
}
