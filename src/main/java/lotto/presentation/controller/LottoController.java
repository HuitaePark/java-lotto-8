package lotto.presentation.controller;

import lotto.application.dto.WinningStaticsDto;
import lotto.application.service.LottoService;
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
        inputWithRetry(this::readInputPurchaseAmount);
        inputWithRetry(this::printIssuedTicket);
        inputWithRetry(this::readWinningNumbers);
        inputWithRetry(this::readBonusNumber);
        readInputWinningNumber();
        inputView.closeConsole();
    }

    private void inputWithRetry(Runnable task) {
        while (true) {
            try {
                task.run();
                return;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private void readInputPurchaseAmount() {
        outputView.printStartMessage();
        String input = inputView.inputText();
        int count = lottoService.getPurchaseCount(input);
        outputView.printPurchaseCount(count);
    }

    private void printIssuedTicket() {
        outputView.printIssuedTicket(lottoService.getIssuedTicket());
    }

    private void readInputWinningNumber() {
        WinningStaticsDto dto = lottoService.processWinningNumbers();
        outputView.printStatics(dto);
    }

    private void readWinningNumbers() {
        outputView.entryMessage();
        String input = inputView.inputText();

        lottoService.getWinningNumbers(input);
    }

    private void readBonusNumber() {
        outputView.bonusMessage();
        String input = inputView.inputText();

        lottoService.getBonusNumbers(input);
    }
}
