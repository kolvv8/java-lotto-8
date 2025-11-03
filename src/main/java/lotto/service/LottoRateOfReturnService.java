package lotto.service;

import java.util.Map;
import lotto.domain.CheckLottoWinningRank;

public class LottoRateOfReturnService {

    public double calculateProfitRate(Map<CheckLottoWinningRank, Integer> statistics,
                                      int purchaseAmount) {
        long totalPrize = calculateTotalPrize(statistics);

        if (purchaseAmount == 0) {
            return 0.0;
        }

        double profitRate = ((double) totalPrize / purchaseAmount) * 100.0;

        return Math.round(profitRate * 10.0) / 10.0;
    }

    private long calculateTotalPrize(Map<CheckLottoWinningRank, Integer> statistics) {
        long totalPrize = 0;
        for (CheckLottoWinningRank rank : statistics.keySet()) {
            totalPrize += rank.getPrize() * statistics.get(rank);
        }
        return totalPrize;
    }
}

