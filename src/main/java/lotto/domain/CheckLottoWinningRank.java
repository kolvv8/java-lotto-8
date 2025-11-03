package lotto.domain;

import java.text.NumberFormat;

public enum CheckLottoWinningRank {
    ZERO(0, false, 0),
    FIFTH(3, false, 5_000),
    FOURTH(4, false, 50_000),
    THIRD(5, false, 1_500_000),
    SECOND(5, true, 30_000_000),
    FIRST(6, false, 2_000_000_000);

    private final int matchCount;
    private final boolean bonusRequired;
    private final long prize;

    CheckLottoWinningRank(int matchCount, boolean bonusRequired, long prize) {
        this.matchCount = matchCount;
        this.bonusRequired = bonusRequired;
        this.prize = prize;
    }

    public long getPrize() {
        return prize;
    }

    public boolean isMiss() {
        return this == ZERO;
    }

    public static CheckLottoWinningRank valueOf(int matchCount, boolean hasBonus) {
        if (matchCount == SECOND.matchCount && hasBonus == SECOND.bonusRequired) {
            return SECOND;
        }

        for (CheckLottoWinningRank rank : values()) {
            if (rank.matchCount == matchCount && !rank.bonusRequired) {
                return rank;
            }
        }

        return ZERO;
    }

    public String getDescription(int count) {
        String prizeFormatted = NumberFormat.getInstance().format(prize);
        String description = String.format("%d개 일치 (%s원) - %d개",
                matchCount, prizeFormatted, count);

        if (this == SECOND) {
            description = String.format("%d개 일치, 보너스 볼 일치 (%s원) - %d개",
                    matchCount, prizeFormatted, count);
        }
        return description;
    }
}

