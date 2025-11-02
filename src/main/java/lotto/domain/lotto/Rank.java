package lotto.domain.lotto;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.stream.Collectors;

public enum Rank {
    FIRST_PLACE(6, false, 2_000_000_000, "6개 일치 (%s원) - %d개"),
    SECOND_PLACE(5, true, 30_000_000, "5개 일치, 보너스 볼 일치 (%s원) - %d개"),
    THIRD_PLACE(5, false, 1_500_000, "5개 일치 (%s원) - %d개"),
    FOURTH_PLACE(4, false, 50_000, "4개 일치 (%s원) - %d개"),
    FIFTH_PLACE(3, false, 5_000, "3개 일치 (%s원) - %d개"),
    MISS(0, false, 0, "꽝");

    private final int matchCount;
    private final boolean matchBonus;
    private final int prize;
    private final String messageFormat;

    Rank(int matchCount, boolean matchBonus, int prize, String messageFormat) {
        this.matchBonus = matchBonus;
        this.matchCount = matchCount;
        this.messageFormat = messageFormat;
        this.prize = prize;
    }

    public static Rank of(int matchCount, boolean bonusMatch) {
        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == matchCount && rank.matchBonus == bonusMatch)
                .findFirst()
                .orElse(MISS);
    }

    public static EnumMap<Rank, Integer> initialize() {
        return Arrays.stream(Rank.values())
                .collect(Collectors.toMap(
                        rank -> rank,
                        value -> 0,
                        (oldValue, newValue) -> newValue, //중복시 밸류값을 물어보는것으로 HashMap의 경우를 위해 추가해야 하지만 EnumMap은 의미없음
                        () -> new EnumMap<>(Rank.class)
                ));
    }

    public int getPrize() {
        return prize;
    }

    public String getMessage(int count) {
        return String.format(messageFormat, String.format("%,d", prize), count);
    }

}
