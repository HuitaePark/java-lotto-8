package lotto.domain.lotto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.domain.error.LottoErrorCode;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public List<Integer> numbers() {
        return numbers;
    }

    private void validate(List<Integer> numbers) {
        verifySize(numbers);
        verifyDuplication(numbers);
        verifyOutOfLange(numbers);
    }

    private void verifySize(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(LottoErrorCode.INVALID_SIZE.getMessage());
        }
    }

    private void verifyDuplication(List<Integer> numbers) {
        Set<Integer> verifySet = new HashSet<>(numbers);
        if (verifySet.size() != 6) {
            throw new IllegalArgumentException(LottoErrorCode.DUPLICATE_NUMBER.getMessage());
        }
    }

    private void verifyOutOfLange(List<Integer> numbers) {
        if (isOutOfLange(numbers)) {
            throw new IllegalArgumentException(LottoErrorCode.OUT_OF_RANGE.getMessage());
        }
    }

    private boolean isOutOfLange(List<Integer> numbers) {
        return numbers.stream()
                .anyMatch(number -> number < 1 || number > 45);
    }

}
