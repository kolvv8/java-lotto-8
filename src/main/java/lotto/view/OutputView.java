package lotto.view;

import java.util.List;
import java.util.Map;
import lotto.domain.CheckLottoWinningRank;
import lotto.domain.Lotto;

public class OutputView {

    public void printPurchaseCount(int count) {
        System.out.println();
        System.out.printf("%d개를 구매했습니다.\n", count);
    }

    public void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }

    public void printStatistics(Map<CheckLottoWinningRank, Integer> statistics) {
        System.out.println("\n당첨 통계\n---");
        for (CheckLottoWinningRank rank : CheckLottoWinningRank.values()) {
            printRankResult(rank, statistics);
        }
    }

    private void printRankResult(CheckLottoWinningRank rank,
                                 Map<CheckLottoWinningRank, Integer> statistics) {
        if (rank.isMiss()) {
            return;
        }
        int count = statistics.getOrDefault(rank, 0);
        System.out.println(rank.getDescription(count));
    }

    public void printProfitRate(double profitRate) {
        System.out.printf("총 수익률은 %.1f%%입니다.\n", profitRate);
    }

    public void printError(String message) {
        System.out.println(message);
    }
}

