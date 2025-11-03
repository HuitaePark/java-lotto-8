package lotto.domain.repository;

import java.util.List;
import lotto.domain.value.BonusNumber;

public interface BonusNumberRepository {
    void save(BonusNumber bonusNumber);

    List<BonusNumber> findAll();

    //저장된 첫번째 요소를 반환
    default BonusNumber findFirst() {
        List<BonusNumber> all = findAll();
        return all.isEmpty() ? null : all.getFirst();
    }
}
