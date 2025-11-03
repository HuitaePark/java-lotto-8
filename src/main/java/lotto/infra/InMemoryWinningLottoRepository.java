package lotto.infra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.domain.lotto.WinningLotto;
import lotto.domain.repository.WinningLottoRepository;

public class InMemoryWinningLottoRepository implements WinningLottoRepository {
    private final List<WinningLotto> winningLottos = new ArrayList<>();


    @Override
    public void save(WinningLotto winningLotto) {
        winningLottos.add(winningLotto);
    }

    @Override
    public List<WinningLotto> findAll() {
        return Collections.unmodifiableList(winningLottos);
    }
}
