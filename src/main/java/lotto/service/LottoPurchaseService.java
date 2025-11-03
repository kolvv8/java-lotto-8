package lotto.service;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoNumberGenerator;
import lotto.util.InputValidator;

public class LottoPurchaseService {

    private final LottoNumberGenerator lottoNumberGenerator;

    public LottoPurchaseService() {
        this.lottoNumberGenerator = new LottoNumberGenerator();
    }

    public List<Lotto> purchaseLottos(int purchaseAmount) {
        int count = purchaseAmount / InputValidator.LOTTO_PRICE;

        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            lottos.add(lottoNumberGenerator.generate());
        }

        return lottos;
    }
}

