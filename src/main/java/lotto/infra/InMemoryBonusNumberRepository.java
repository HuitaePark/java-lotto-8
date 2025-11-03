package lotto.infra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.domain.repository.BonusNumberRepository;
import lotto.domain.value.BonusNumber;

public class InMemoryBonusNumberRepository implements BonusNumberRepository {
    private final List<BonusNumber> bonusNumbers = new ArrayList<>();

    @Override
    public void save(BonusNumber bonusNumber) {
        bonusNumbers.add(bonusNumber);
    }

    @Override
    public List<BonusNumber> findAll() {
        return Collections.unmodifiableList(bonusNumbers);
    }
}
