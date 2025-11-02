package lotto.domain.lotto;

import java.util.List;

public class IssuedLotto extends Lotto {

    public IssuedLotto(List<Integer> issued) {
        super(issued);
    }

    public int countMatching(WinningLotto winningLotto) {
        return (int) super.numbers().stream()
                .filter(winningLotto::isWinningNumber)
                .count();
    }

    public boolean contains(int bonusNumber) {
        return super.numbers().contains(bonusNumber);
    }
}
