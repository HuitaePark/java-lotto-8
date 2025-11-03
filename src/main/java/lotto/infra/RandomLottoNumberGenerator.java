package lotto.infra;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import lotto.domain.LottoNumberGenerator;

public class RandomLottoNumberGenerator implements LottoNumberGenerator {
    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;
    private static final int LOTTO_COUNT = 6;

    @Override
    public List<Integer> generate() {
        return Randoms.pickUniqueNumbersInRange(LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER, LOTTO_COUNT);
    }
}
