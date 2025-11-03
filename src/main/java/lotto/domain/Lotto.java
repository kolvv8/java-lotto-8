package lotto.domain;

import java.util.List;

public class Lotto {

    public static final int LOTTO_SIZE = 6;
    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 45;

    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String ERROR_INVALID_SIZE = "로또 번호는 " + LOTTO_SIZE + "개이어야 합니다.";

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ERROR_PREFIX + ERROR_INVALID_SIZE);
        }
    }
}
