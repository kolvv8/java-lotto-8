package lotto.domain;

import java.util.List;

public class WinningNumbers {

    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String ERROR_OUT_OF_RANGE =
            "로또 번호는 " + Lotto.MIN_NUMBER + "부터 " + Lotto.MAX_NUMBER + " 사이의 숫자여야 합니다.";
    private static final String ERROR_BONUS_DUPLICATE = "보너스 번호가 당첨 번호와 중복됩니다.";

    private final List<Integer> numbers;
    private final int bonusNumber;

    public WinningNumbers(Lotto winningLotto, int bonusNumber) {
        this.numbers = winningLotto.getNumbers();

        validateBonusNumber(bonusNumber);
        validateBonusDuplication(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumber(int bonus) {
        if (bonus < Lotto.MIN_NUMBER || bonus > Lotto.MAX_NUMBER) {
            throw new IllegalArgumentException(ERROR_PREFIX + ERROR_OUT_OF_RANGE);
        }
    }

    private void validateBonusDuplication(int bonus) {
        if (numbers.contains(bonus)) {
            throw new IllegalArgumentException(ERROR_PREFIX + ERROR_BONUS_DUPLICATE);
        }
    }

    public CheckLottoWinningRank calculateRank(Lotto lotto) {
        int matchCount = lotto.countMatch(numbers);
        boolean hasBonus = lotto.contains(bonusNumber);

        return CheckLottoWinningRank.valueOf(matchCount, hasBonus);
    }
}

