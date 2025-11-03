package lotto.domain;

import lotto.domain.lotto.IssuedLotto;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.WinningLotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 1~45를 넘어가는 숫자가 있으면 예외가 발생한다.")
    @Test
    void whenLottoNumberOutOfRange() {
        assertThatThrownBy(() -> new Lotto(List.of(91, 82, 73, 46, 55, 65)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또의 번호가 오름차순으로 정렬되지 않았을 경우 에러가 발생된다..")
    @Test
    void issue_sorted_lotto() {
        assertThatThrownBy(() -> new IssuedLotto(List.of(2, 1, 4, 6, 7, 8)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 오름차순으로 정렬되어야 합니다.");
    }

    @DisplayName("당첨 번호에 발급된 로또번호가 있는지 검증한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    void verify_winning_number(int input) {
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6));
        assertThat(winningLotto.isWinningNumber(input)).isTrue();
    }

    @DisplayName("당첨 번호에 발급된 로또번호가 없는지 검증한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    void verify_fail_number(int input) {
        WinningLotto winningLotto = new WinningLotto(List.of(7, 8, 9, 10, 11, 12));
        assertThat(winningLotto.isWinningNumber(input)).isFalse();
    }

    @DisplayName("0~6개 맞춘 개수만큼 숫자를 반환한다.")
    @Test
    void countMatching_allCases() {
        // 당첨 번호를 1, 2, 3, 4, 5, 6로 가정
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6));

        assertThat(new IssuedLotto(List.of(7, 8, 9, 10, 11, 12)).countMatching(winningLotto)).isEqualTo(0);
        assertThat(new IssuedLotto(List.of(1, 8, 9, 10, 11, 12)).countMatching(winningLotto)).isEqualTo(1);
        assertThat(new IssuedLotto(List.of(1, 2, 9, 10, 11, 12)).countMatching(winningLotto)).isEqualTo(2);
        assertThat(new IssuedLotto(List.of(1, 2, 3, 10, 11, 12)).countMatching(winningLotto)).isEqualTo(3);
        assertThat(new IssuedLotto(List.of(1, 2, 3, 4, 11, 12)).countMatching(winningLotto)).isEqualTo(4);
        assertThat(new IssuedLotto(List.of(1, 2, 3, 4, 5, 12)).countMatching(winningLotto)).isEqualTo(5);
        assertThat(new IssuedLotto(List.of(1, 2, 3, 4, 5, 6)).countMatching(winningLotto)).isEqualTo(6);
    }

    @DisplayName("getIssuedLotto는 내부 List와 값이 같고 불변이어야 한다")
    @Test
    void getIssuedLotto_returnsUnmodifiableCopy() {
        List<Integer> original = List.of(1, 2, 3, 4, 5, 6);
        IssuedLotto issuedLotto = new IssuedLotto(original);

        // 값 동일성 검증
        assertThat(issuedLotto.getIssuedLotto()).containsExactlyElementsOf(original);

        // 불변성 검증
        List<Integer> copy = issuedLotto.getIssuedLotto();
        assertThatThrownBy(() -> copy.add(7))
                .isInstanceOf(UnsupportedOperationException.class);
        assertThatThrownBy(copy::removeFirst)
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
