package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

}
