package lotto.domain.lotto;

import java.util.List;
import java.util.stream.IntStream;

public class IssuedLotto extends Lotto {

    public IssuedLotto(List<Integer> issued) {
        super(issued);
        verifyIsSorted(issued);
    }

    public int countMatching(WinningLotto winningLotto) {
        return (int) super.numbers().stream().filter(winningLotto::isWinningNumber).count();
    }

    public List<Integer> getIssuedLotto() {
        return List.copyOf(super.numbers());
    }

    private void verifyIsSorted(List<Integer> numbers) {
        if (!isSorted(numbers)) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 오름차순으로 정렬되어야 합니다.");
        }
    }

    private boolean isSorted(List<Integer> numbers) {
        return IntStream.range(0, numbers.size() - 1)
                .allMatch(i -> numbers.get(i) <= numbers.get(i + 1));
    }
}
