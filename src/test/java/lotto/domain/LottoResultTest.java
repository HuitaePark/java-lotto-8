package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.domain.lotto.BonusNumber;
import lotto.domain.lotto.IssuedLotto;
import lotto.domain.lotto.LottoResult;
import lotto.domain.lotto.Rank;
import lotto.domain.lotto.WinningLotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoResultTest {

    @DisplayName("발행한 로또목록과 당첨번호,보너스 번호를 비교하여 0개가 일치하는걸 찾는다.")
    @Test
    void Compare_the_published_lotto() {
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6));
        LottoDrawService lottoDrawService = new LottoDrawService(() -> List.of(11, 12, 13, 14, 15, 16));
        List<IssuedLotto> issuedLottos = lottoDrawService.draw(1);
        BonusNumber bonusNumber = new BonusNumber(7);

        LottoResult lottoResult = LottoResult.of(winningLotto, bonusNumber.value(), issuedLottos);

        assertThat(lottoResult.getCountByRank(Rank.MISS)).isEqualTo(1);
    }

    @DisplayName("발행한 로또목록과 당첨번호,보너스 번호를 비교하여 3개가 일치하는걸 찾는다.")
    @Test
    void compare_The_Published_Lotto_Fifth() {
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6));
        LottoDrawService lottoDrawService = new LottoDrawService(() -> List.of(1, 2, 3, 14, 15, 16));
        List<IssuedLotto> issuedLottos = lottoDrawService.draw(1);
        BonusNumber bonusNumber = new BonusNumber(7);

        LottoResult lottoResult = LottoResult.of(winningLotto, bonusNumber.value(), issuedLottos);

        assertThat(lottoResult.getCountByRank(Rank.FIFTH_PLACE)).isEqualTo(1);
    }

    @DisplayName("발행한 로또목록과 당첨번호,보너스 번호를 비교하여 4개가 일치하는걸 찾는다.")
    @Test
    void compare_The_Published_Lotto_Fourth() {
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6));
        LottoDrawService lottoDrawService = new LottoDrawService(() -> List.of(1, 2, 3, 4, 15, 16));
        List<IssuedLotto> issuedLottos = lottoDrawService.draw(1);
        BonusNumber bonusNumber = new BonusNumber(7);

        LottoResult lottoResult = LottoResult.of(winningLotto, bonusNumber.value(), issuedLottos);

        assertThat(lottoResult.getCountByRank(Rank.FOURTH_PLACE)).isEqualTo(1);
    }

    @DisplayName("발행한 로또목록과 당첨번호,보너스 번호를 비교하여 5개가 일치하는걸 찾는다.")
    @Test
    void Compare_the_published_lotto_Third() {
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6));
        LottoDrawService lottoDrawService = new LottoDrawService(() -> List.of(1, 2, 3, 4, 5, 16));
        List<IssuedLotto> issuedLottos = lottoDrawService.draw(1);
        BonusNumber bonusNumber = new BonusNumber(7);

        LottoResult lottoResult = LottoResult.of(winningLotto, bonusNumber.value(), issuedLottos);

        assertThat(lottoResult.getCountByRank(Rank.THIRD_PLACE)).isEqualTo(1);
    }

    @DisplayName("발행한 로또목록과 당첨번호,보너스 번호를 비교하여 5개와 보너스가 일치하는걸 찾는다.")
    @Test
    void Compare_the_published_lotto_SECOND() {
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 7));
        LottoDrawService lottoDrawService = new LottoDrawService(() -> List.of(1, 2, 3, 4, 5, 17));
        List<IssuedLotto> issuedLottos = lottoDrawService.draw(1);
        BonusNumber bonusNumber = new BonusNumber(7);

        LottoResult lottoResult = LottoResult.of(winningLotto, bonusNumber.value(), issuedLottos);

        assertThat(lottoResult.getCountByRank(Rank.SECOND_PLACE)).isEqualTo(1);
    }

    @DisplayName("발행한 로또목록과 당첨번호,보너스 번호를 비교하여 6개가 일치하는걸 찾는다.")
    @Test
    void Compare_the_published_lotto_FIRST() {
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6));
        LottoDrawService lottoDrawService = new LottoDrawService(() -> List.of(1, 2, 3, 4, 5, 6));
        List<IssuedLotto> issuedLottos = lottoDrawService.draw(1);
        BonusNumber bonusNumber = new BonusNumber(7);

        LottoResult lottoResult = LottoResult.of(winningLotto, bonusNumber.value(), issuedLottos);

        assertThat(lottoResult.getCountByRank(Rank.FIRST_PLACE)).isEqualTo(1);
    }
}
