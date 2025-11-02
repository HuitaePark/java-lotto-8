package lotto.infra;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import lotto.domain.LottoNumberGenerator;

public class RandomLottoNumberGenerator implements LottoNumberGenerator {
    @Override
    public List<Integer> generate() {
        return Randoms.pickUniqueNumbersInRange(1, 45, 6);
    }
}
