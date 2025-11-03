package lotto.controller;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import lotto.domain.CheckLottoWinningRank;
import lotto.domain.Lotto;
import lotto.domain.WinningNumbers;
import lotto.service.LottoPurchaseService;
import lotto.service.LottoRateOfReturnService;
import lotto.service.LottoStatisticsService;
import lotto.util.InputValidator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoPurchaseService lottoPurchaseService;
    private final LottoStatisticsService lottoStatisticsService;
    private final LottoRateOfReturnService lottoRateOfReturnService;

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.lottoPurchaseService = new LottoPurchaseService();
        this.lottoStatisticsService = new LottoStatisticsService();
        this.lottoRateOfReturnService = new LottoRateOfReturnService();
    }

    public void run() {
        int purchaseAmount = getValidPurchaseAmount();
        List<Lotto> lottos = lottoPurchaseService.purchaseLottos(purchaseAmount);
        outputView.printPurchaseCount(lottos.size());
        outputView.printLottos(lottos);

        WinningNumbers winningNumbers = getValidWinningNumbers();

        Map<CheckLottoWinningRank, Integer> statistics =
                lottoStatisticsService.calculateResults(lottos, winningNumbers);
        double profitRate =
                lottoRateOfReturnService.calculateProfitRate(statistics, purchaseAmount);

        outputView.printStatistics(statistics);
        outputView.printProfitRate(profitRate);
    }

    private <T> T retryOnIllegalArgumentException(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private int getValidPurchaseAmount() {
        return retryOnIllegalArgumentException(() ->
                InputValidator.validatePurchaseAmount(inputView.readPurchaseAmount())
        );
    }

    private WinningNumbers getValidWinningNumbers() {
        Lotto winningLotto = getValidWinningLotto();

        return retryOnIllegalArgumentException(() -> {
            int bonusNumber = InputValidator.parseBonusNumber(
                    inputView.readBonusNumber()
            );

            return new WinningNumbers(winningLotto, bonusNumber);
        });
    }

    private Lotto getValidWinningLotto() {
        return retryOnIllegalArgumentException(() -> {
            List<Integer> numbers = InputValidator.parseWinningNumbers(
                    inputView.readWinningNumbers()
            );
            return new Lotto(numbers);
        });
    }

}
