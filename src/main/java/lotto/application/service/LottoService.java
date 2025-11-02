package lotto.application.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lotto.application.dto.IssuedLottoDto;
import lotto.application.dto.WinningStaticsDto;
import lotto.application.util.InputParser;
import lotto.domain.DrawService;
import lotto.domain.ReturnCalculator;
import lotto.domain.lotto.IssuedLotto;
import lotto.domain.lotto.LottoResult;
import lotto.domain.lotto.Rank;
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
        LottoResult result = LottoResult.of(winningLotto, bonusNumber.value(), lottoRepository.findAll());
        int amount = lottoRepository.findAll().size();
        String statics = findStatics(result);
        double yield = ReturnCalculator.calculate(amount, result.getResults());
        return new WinningStaticsDto(statics, yield);
    }

    private void issuedLotto(int count) {
        DrawService drawService = new DrawService(new RandomLottoNumberGenerator());
        lottoRepository.saveAll(drawService.draw(count));
    }

    private String findStatics(LottoResult result) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank != Rank.MISS)
                .map(rank -> rank.getMessage(result.getCountByRank(rank)))
                .sorted(Collections.reverseOrder())
                .collect(Collectors.joining(System.lineSeparator()));
    }

}
