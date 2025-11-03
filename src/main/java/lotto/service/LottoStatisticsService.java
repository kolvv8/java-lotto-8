package lotto.service;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lotto.domain.CheckLottoWinningRank;
import lotto.domain.Lotto;
import lotto.domain.WinningNumbers;

public class LottoStatisticsService {

    public Map<CheckLottoWinningRank, Integer> calculateResults(List<Lotto> lottos,
                                                                WinningNumbers winningNumbers) {
        Map<CheckLottoWinningRank, Integer> statistics = initializeStatisticsMap();

        for (Lotto lotto : lottos) {
            CheckLottoWinningRank rank = winningNumbers.calculateRank(lotto);
            if (!rank.isMiss()) {
                statistics.put(rank, statistics.get(rank) + 1);
            }
        }
        return statistics;
    }

    private Map<CheckLottoWinningRank, Integer> initializeStatisticsMap() {
        Map<CheckLottoWinningRank, Integer> statistics =
                new EnumMap<>(CheckLottoWinningRank.class);

        for (CheckLottoWinningRank rank : CheckLottoWinningRank.values()) {
            if (!rank.isMiss()) {
                statistics.put(rank, 0);
            }
        }
        return statistics;
    }
}

