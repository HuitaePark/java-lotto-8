package lotto.domain.lotto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public List<Integer> numbers() {
        return numbers;
    }

    public void checkBonusNumberDuplicate(int bonusNumber) {
        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 로또번호와 중복될수 없습니다.");
        }
    }

    private void validate(List<Integer> numbers) {
        verifySize(numbers);
        verifyDuplication(numbers);
        verifyOutOfLange(numbers);
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

    private void verifyOutOfLange(List<Integer> numbers) {
        if (isOutOfLange(numbers)) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1보다 작거나 45보다 클수 없습니다.");
        }
    }

    private boolean isOutOfLange(List<Integer> numbers) {
        return numbers.stream()
                .anyMatch(number -> number < 1 || number > 45);
    }

}
