package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.domain.value.BonusNumber;
import lotto.domain.lotto.IssuedLotto;
import lotto.domain.lotto.LottoResult;
import lotto.domain.lotto.Rank;
import lotto.domain.lotto.WinningLotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoResultTest {

    private static final int BONUS_NUMBER = 7;

    private LottoResult createLottoResult(List<Integer> winningNumbers, List<Integer> issuedNumbers) {
        WinningLotto winningLotto = new WinningLotto(winningNumbers);
        DrawService drawService = new DrawService(() -> issuedNumbers);
        List<IssuedLotto> issuedLottos = drawService.draw(1);
        BonusNumber bonusNumber = new BonusNumber(BONUS_NUMBER);

        return LottoResult.of(winningLotto, bonusNumber.value(), issuedLottos);
    }

    private void assertRank(LottoResult result, Rank rank) {
        assertThat(result.getCountByRank(rank)).isEqualTo(1);
    }

    @DisplayName("0개가 일치")
    @Test
    void matchZero() {
        LottoResult result = createLottoResult(List.of(1, 2, 3, 4, 5, 6), List.of(11, 12, 13, 14, 15, 16));
        assertRank(result, Rank.MISS);
    }

    @DisplayName("1개가 일치")
    @Test
    void matchOne() {
        LottoResult result = createLottoResult(List.of(1, 2, 3, 4, 5, 6), List.of(1, 12, 13, 14, 15, 16));
        assertRank(result, Rank.MISS);
    }

    @DisplayName("2개가 일치")
    @Test
    void matchTwo() {
        LottoResult result = createLottoResult(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 13, 14, 15, 16));
        assertRank(result, Rank.MISS);
    }

    @DisplayName("3개가 일치")
    @Test
    void matchThree() {
        LottoResult result = createLottoResult(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 3, 14, 15, 16));
        assertRank(result, Rank.FIFTH_PLACE);
    }

    @DisplayName("4개가 일치")
    @Test
    void matchFour() {
        LottoResult result = createLottoResult(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 3, 4, 15, 16));
        assertRank(result, Rank.FOURTH_PLACE);
    }

    @DisplayName("5개가 일치")
    @Test
    void matchFive() {
        LottoResult result = createLottoResult(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 3, 4, 5, 16));
        assertRank(result, Rank.THIRD_PLACE);
    }

    @DisplayName("5개와 보너스 번호가 일치")
    @Test
    void matchFivePlusBonus() {
        LottoResult result = createLottoResult(List.of(1, 2, 3, 4, 5, 7), List.of(1, 2, 3, 4, 5, 17));
        assertRank(result, Rank.SECOND_PLACE);
    }

    @DisplayName("6개가 일치")
    @Test
    void matchSix() {
        LottoResult result = createLottoResult(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 3, 4, 5, 6));
        assertRank(result, Rank.FIRST_PLACE);
    }
}
