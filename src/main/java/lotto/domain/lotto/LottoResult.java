package lotto.domain.lotto;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public Map<Rank, Integer> getResults() {
        return Collections.unmodifiableMap(new EnumMap<>(results));
    }

    public String findStatics() {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank != Rank.MISS)
                .sorted(Collections.reverseOrder())
                .map(rank -> rank.getMessage(getCountByRank(rank)))
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
