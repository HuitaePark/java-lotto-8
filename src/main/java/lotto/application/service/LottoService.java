package lotto.application.service;

import java.util.List;
import lotto.application.dto.IssuedLottoDto;
import lotto.application.dto.WinningStaticsDto;
import lotto.application.util.InputParser;
import lotto.domain.DrawService;
import lotto.domain.lotto.IssuedLotto;
import lotto.domain.lotto.WinningLotto;
import lotto.domain.repository.LottoRepository;
import lotto.domain.value.BonusNumber;
import lotto.domain.value.PurchaseAmount;
import lotto.infra.RandomLottoNumberGenerator;

public class LottoService {
    private final LottoRepository lottoRepository;

    public LottoService(LottoRepository lottoRepository) {
        this.lottoRepository = lottoRepository;
    }

    public int getPurchaseCount(String input) {
        int money = InputParser.parseToInt(input);
        PurchaseAmount purchaseAmount = new PurchaseAmount(money);

        int count = purchaseAmount.getQuantity();
        issuedLotto(count);

        return count;
    }

    public List<IssuedLottoDto> getIssuedTicket() {
        List<IssuedLotto> issuedLottos = lottoRepository.findAll();
        return issuedLottos.stream()
                .map(IssuedLottoDto::from)
                .toList();
    }

    public WinningLotto getWinningNumbers(String input) {
        List<Integer> numbers = InputParser.parseToList(input);
        return new WinningLotto(numbers);
    }

    public BonusNumber getBonusNumbers(String input) {
        int number = InputParser.parseToInt(input);
        return new BonusNumber(number);
    }

    public WinningStaticsDto processWinningNumbers(WinningLotto winningLotto, BonusNumber bonusNumber) {

    }

    private void issuedLotto(int count) {
        DrawService drawService = new DrawService(new RandomLottoNumberGenerator());
        lottoRepository.saveAll(drawService.draw(count));
    }
}
