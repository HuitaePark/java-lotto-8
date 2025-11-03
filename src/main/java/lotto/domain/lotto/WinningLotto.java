package lotto.domain.lotto;

import java.util.List;

public class WinningLotto extends Lotto {

    public WinningLotto(List<Integer> winnings) {
        super(winnings);
    }

    public boolean isWinningNumber(int number) {
        return super.numbers().contains(number);
    }

    public void checkBonusNumberDuplicate(int bonusNumber) {
        if (super.numbers().contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 로또번호와 중복될수 없습니다.");
        }
    }
}
