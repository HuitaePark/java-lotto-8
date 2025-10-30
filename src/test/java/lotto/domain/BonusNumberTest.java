package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BonusNumberTest {

    @DisplayName("보너스 번호가 범위를 초과할경우 에러가 발생한다.")
    @Test
    void when_BonusNumber_OutOfRange() {
        assertThatThrownBy(() -> new BonusNumber(46))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("보너스 넘버는 1에서 45 사이여야 합니다.");
    }

    @DisplayName("보너스 번호가 로또와 중복될 경우 에러가 발생한다.")
    @Test
    void when_BonusNumber_duplicate_with_lotto() {
        assertThatThrownBy(() -> {
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
            BonusNumber bonusNumber = new BonusNumber(1);

            lotto.checkBonusNumberDuplicate(bonusNumber.number());

        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("보너스 번호는 로또번호와 중복될수 없습니다.");
    }

}
