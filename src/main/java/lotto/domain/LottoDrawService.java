package lotto.domain;

import java.util.List;
import java.util.stream.IntStream;

public class LottoDrawService {
    private final LottoNumberGenerator lottoNumberGenerator;

    public LottoDrawService(LottoNumberGenerator lottoNumberGenerator) {
        this.lottoNumberGenerator = lottoNumberGenerator;
    }

    public List<Lotto> draw(int quantity) {
        return IntStream.range(0, quantity)
                .mapToObj(i -> sortInAscendingOrder(lottoNumberGenerator.generate()))
                .map(Lotto::new)
                .toList();
    }

    private List<Integer> sortInAscendingOrder(List<Integer> numbers) {
        return numbers.stream()
                .sorted()
                .toList();
    }

}
