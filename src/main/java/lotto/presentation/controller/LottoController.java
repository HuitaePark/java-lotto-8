package lotto.presentation.controller;

import java.util.function.Supplier;
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
        inputWithRetry(this::readInputPurchaseAmount);
        inputWithRetry(this::printIssuedTicket);
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

    private <T> T inputWithRetry(Supplier<T> task) {
        while (true) {
            try {
                return task.get();
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
        WinningStaticsDto dto = lottoService.processWinningNumbers(
                inputWithRetry(this::readWinningNumbers),
                inputWithRetry(this::readBonusNumber));
        outputView.printStatics(dto);
    }

    private WinningLotto readWinningNumbers() {
        outputView.entryMessage();
        String input = inputView.inputText();

        return lottoService.getWinningNumbers(input);

    }

    private BonusNumber readBonusNumber() {
        outputView.bonusMessage();
        String input = inputView.inputText();

        return lottoService.getBonusNumbers(input);

    }
}
