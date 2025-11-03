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
import lotto.domain.repository.BonusNumberRepository;
import lotto.domain.repository.IssuedLottoRepository;
import lotto.domain.repository.WinningLottoRepository;
import lotto.domain.value.BonusNumber;
import lotto.domain.value.PurchaseAmount;
import lotto.infra.RandomLottoNumberGenerator;

public class LottoService {
    private final IssuedLottoRepository issuedLottoRepository;
    private final BonusNumberRepository bonusNumberRepository;
    private final WinningLottoRepository winningLottoRepository;

    public LottoService(BonusNumberRepository bonusNumberRepository,
                        IssuedLottoRepository issuedLottoRepository,
                        WinningLottoRepository winningLottoRepository) {
        this.bonusNumberRepository = bonusNumberRepository;
        this.issuedLottoRepository = issuedLottoRepository;
        this.winningLottoRepository = winningLottoRepository;
    }

    public int getPurchaseCount(String input) {
        int money = InputParser.parseToInt(input);
        PurchaseAmount purchaseAmount = new PurchaseAmount(money);

        int count = purchaseAmount.getQuantity();
        issuedLotto(count);

        return count;
    }

    public List<IssuedLottoDto> getIssuedTicket() {
        List<IssuedLotto> issuedLottos = issuedLottoRepository.findAll();
        return issuedLottos.stream()
                .map(IssuedLottoDto::from)
                .toList();
    }

    public void getWinningNumbers(String input) {
        List<Integer> numbers = InputParser.parseToList(input);
        winningLottoRepository.save(new WinningLotto(numbers));
    }

    public void getBonusNumbers(String input) {
        int number = InputParser.parseToInt(input);
        bonusNumberRepository.save(new BonusNumber(number));
    }

    public WinningStaticsDto processWinningNumbers() {
        LottoResult result = LottoResult.of(winningLottoRepository.findFirst(),
                bonusNumberRepository.findFirst().value(),
                issuedLottoRepository.findAll());

        int amount = issuedLottoRepository.findAll().size();
        String statics = findStatics(result);
        double yield = ReturnCalculator.calculate(amount, result.getResults());
        return new WinningStaticsDto(statics, yield);
    }

    private void issuedLotto(int count) {
        DrawService drawService = new DrawService(new RandomLottoNumberGenerator());
        issuedLottoRepository.saveAll(drawService.draw(count));
    }

    private String findStatics(LottoResult result) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank != Rank.MISS)
                .map(rank -> rank.getMessage(result.getCountByRank(rank)))
                .sorted(Collections.reverseOrder())
                .collect(Collectors.joining(System.lineSeparator()));
    }

}
