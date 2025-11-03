package lotto.domain.repository;

import java.util.List;
import lotto.domain.lotto.WinningLotto;

public interface WinningLottoRepository {
    void save(WinningLotto winningLotto);

    List<WinningLotto> findAll();

    //저장된 첫번째 요소를 반환
    default WinningLotto findFirst() {
        List<WinningLotto> all = findAll();
        return all.isEmpty() ? null : all.getFirst();
    }
}
