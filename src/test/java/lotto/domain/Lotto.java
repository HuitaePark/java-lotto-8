package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        verifySize(numbers);
        verifyDuplication(numbers);
        this.numbers = numbers;
    }

    private void verifySize(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    private void verifyDuplication(List<Integer> numbers) {
        Set<Integer> verifySet = new HashSet<>(numbers);
        if (verifySet.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 중복될수 없습니다.");
        }
    }

}
