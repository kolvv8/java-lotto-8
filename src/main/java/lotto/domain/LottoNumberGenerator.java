package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class LottoNumberGenerator {

    public Lotto generate() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(
                Lotto.MIN_NUMBER,
                Lotto.MAX_NUMBER,
                Lotto.LOTTO_SIZE
        );
        return new Lotto(numbers);
    }
}

