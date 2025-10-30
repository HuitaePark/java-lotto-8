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
                .mapToObj(i -> lottoNumberGenerator.generate())
                .map(Lotto::new)
                .sorted()
                .toList();
    }

}
