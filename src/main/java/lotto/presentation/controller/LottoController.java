package lotto.presentation.controller;

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

    
}
