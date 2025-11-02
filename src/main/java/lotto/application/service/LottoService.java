package lotto.application.service;

import lotto.application.util.InputParser;
import lotto.domain.DrawService;
import lotto.domain.repository.LottoRepository;
import lotto.domain.value.PurchaseAmount;
import lotto.infra.RandomLottoNumberGenerator;

public class LottoService {
    private final LottoRepository lottoRepository;

    public LottoService(LottoRepository lottoRepository) {
        this.lottoRepository = lottoRepository;
    }

    public int getPurchaseCount(String input) {
        int money = InputParser.parse(input);
        PurchaseAmount purchaseAmount = new PurchaseAmount(money);

        int count = purchaseAmount.getQuantity();
        issuedLotto(count);

        return count;
    }

    public String getIssuedTicket() {

    }

    private void issuedLotto(int count) {
        DrawService drawService = new DrawService(new RandomLottoNumberGenerator());
        lottoRepository.saveAll(drawService.draw(count));
    }
}
