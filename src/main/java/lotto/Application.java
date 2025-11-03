package lotto;

import lotto.application.service.LottoService;
import lotto.infra.InMemoryBonusNumberRepository;
import lotto.infra.InMemoryIssuedLottoRepository;
import lotto.infra.InMemoryWinningLottoRepository;
import lotto.presentation.controller.LottoController;
import lotto.presentation.ui.InputView;
import lotto.presentation.ui.OutputView;

public class Application {
    public static void main(String[] args) {
        LottoController controller = new LottoController(
                new InputView(),
                new OutputView(),
                new LottoService(
                        new InMemoryBonusNumberRepository(),
                        new InMemoryIssuedLottoRepository(),
                        new InMemoryWinningLottoRepository())
        );
        controller.run();
    }
}
