package lotto.domain.lotto;

import java.util.List;
import lotto.domain.error.LottoErrorCode;

public class WinningLotto extends Lotto {

    public WinningLotto(List<Integer> winnings) {
        super(winnings);
    }

    public boolean isWinningNumber(int number) {
        return super.numbers().contains(number);
    }

    public void checkBonusNumberDuplicate(int bonusNumber) {
        if (super.numbers().contains(bonusNumber)) {
            throw new IllegalArgumentException(LottoErrorCode.DUPLICATE_WINNER.getMessage());
        }
    }
}
