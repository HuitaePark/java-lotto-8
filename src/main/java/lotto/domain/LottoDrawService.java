package lotto.domain;

import java.util.List;
import java.util.stream.IntStream;
import lotto.domain.lotto.IssuedLotto;
import lotto.domain.lotto.LottoNumberGenerator;

public class LottoDrawService {
    private final LottoNumberGenerator lottoNumberGenerator;

    public LottoDrawService(LottoNumberGenerator lottoNumberGenerator) {
        this.lottoNumberGenerator = lottoNumberGenerator;
    }

    public List<IssuedLotto> draw(int quantity) {
        return IntStream.range(0, quantity)
                .mapToObj(i -> sortInAscendingOrder(lottoNumberGenerator.generate()))
                .map(IssuedLotto::new)
                .toList();
    }

    private List<Integer> sortInAscendingOrder(List<Integer> numbers) {
        return numbers.stream()
                .sorted()
                .toList();
    }

}
