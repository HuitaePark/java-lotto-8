package lotto.domain;

import java.util.List;
import java.util.stream.IntStream;
import lotto.domain.lotto.IssuedLotto;

public class DrawService {
    private final LottoNumberGenerator lottoNumberGenerator;

    public DrawService(LottoNumberGenerator lottoNumberGenerator) {
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
