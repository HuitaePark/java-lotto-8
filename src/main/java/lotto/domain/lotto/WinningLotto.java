package lotto.domain.lotto;

import java.util.List;

public class WinningLotto extends Lotto {

    public WinningLotto(List<Integer> winnings) {
        super(winnings);
    }

    boolean isWinningNumber(int number) {
        return super.numbers().contains(number);
    }
}
