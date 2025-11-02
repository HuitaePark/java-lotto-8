package lotto.domain.repository;

import java.util.List;
import lotto.domain.lotto.IssuedLotto;

public interface LottoRepository {
    void saveAll(List<IssuedLotto> draw);

    List<IssuedLotto> findAll();
}
