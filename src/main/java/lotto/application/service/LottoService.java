package lotto.application.service;

import lotto.domain.repository.LottoRepository;
import lotto.domain.value.PurchaseAmount;

public class LottoService {
    private final LottoRepository lottoRepository;

    public LottoService(LottoRepository lottoRepository) {
        this.lottoRepository = lottoRepository;
    }

    public int getPurchaseCount(String input) {
        int money = InputParser.parse(input);
        PurchaseAmount purchaseAmount = new PurchaseAmount(money);
        
        return purchaseAmount.getQuantity();
    }
}
