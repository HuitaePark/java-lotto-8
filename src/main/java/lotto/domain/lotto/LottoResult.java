package lotto.domain.lotto;

import java.util.EnumMap;
import java.util.List;

public class LottoResult {
    private final EnumMap<Rank, Integer> results;

    private LottoResult(EnumMap<Rank, Integer> results) {
        this.results = results;
    }

    public static LottoResult of(WinningLotto winningLotto, int bonusNumber, List<IssuedLotto> issuedLottos) {
        EnumMap<Rank, Integer> resultMap = Rank.initialize();

        issuedLottos.forEach(issuedLotto -> {
            int matchCount = issuedLotto.countMatching(winningLotto);
            boolean bonusMatch = winningLotto.isWinningNumber(bonusNumber);
            Rank rank = Rank.of(matchCount, bonusMatch);
            resultMap.put(rank, resultMap.get(rank) + 1);
        });

        return new LottoResult(resultMap);
    }

    public int getCountByRank(Rank rank) {
        return results.get(rank);
    }
}
