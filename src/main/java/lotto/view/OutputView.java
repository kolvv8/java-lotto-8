package lotto.view;

import java.util.List;
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
}
