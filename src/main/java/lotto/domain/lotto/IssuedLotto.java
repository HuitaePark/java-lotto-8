package lotto.domain.lotto;

import java.util.List;
import java.util.stream.IntStream;
import lotto.domain.error.LottoErrorCode;

public class IssuedLotto extends Lotto {

    public IssuedLotto(List<Integer> issued) {
        super(issued);
        verifyIsSorted(issued);
    }

    public int countMatching(WinningLotto winningLotto) {
        return (int) super.numbers().stream()
                .filter(winningLotto::isWinningNumber)
                .count();
    }

    public List<Integer> getIssuedLotto() {
        return List.copyOf(super.numbers());
    }

    private void verifyIsSorted(List<Integer> numbers) {
        if (!isSorted(numbers)) {
            throw new IllegalArgumentException(LottoErrorCode.INVALID_SORT.getMessage());
        }
    }

    private boolean isSorted(List<Integer> numbers) {
        return IntStream.range(0, numbers.size() - 1)
                .allMatch(i -> numbers.get(i) <= numbers.get(i + 1));
    }
}
