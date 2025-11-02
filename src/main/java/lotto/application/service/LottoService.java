package lotto.application.service;

import java.util.List;
import lotto.application.dto.IssuedLottoDto;
import lotto.application.util.InputParser;
import lotto.domain.DrawService;
import lotto.domain.lotto.IssuedLotto;
import lotto.domain.repository.LottoRepository;
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

    private void issuedLotto(int count) {
        DrawService drawService = new DrawService(new RandomLottoNumberGenerator());
        lottoRepository.saveAll(drawService.draw(count));
    }
}
