package lotto.infra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.domain.lotto.IssuedLotto;
import lotto.domain.repository.IssuedLottoRepository;

public class InMemoryIssuedLottoRepository implements IssuedLottoRepository {
    private final List<IssuedLotto> tickets = new ArrayList<>();

    @Override
    public void saveAll(List<IssuedLotto> draw) {
        tickets.addAll(draw);
    }

    @Override
    public List<IssuedLotto> findAll() {
        return Collections.unmodifiableList(tickets);
    }
}
