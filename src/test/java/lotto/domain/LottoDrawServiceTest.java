package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoDrawServiceTest {

    @DisplayName("구매횟수만큼 로또를 발행한다.")
    @Test
    void draw_lotto_success() {
        LottoDrawService lottoDrawService = new LottoDrawService(() -> List.of(1, 2, 3, 4, 5, 6));

        List<IssuedLotto> lottos = lottoDrawService.draw(3);

        assertThat(lottos).hasSize(3);
    }
}
