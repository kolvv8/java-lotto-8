package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {

    public static final int LOTTO_SIZE = 6;
    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 45;

    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String ERROR_INVALID_SIZE = "로또 번호는 " + LOTTO_SIZE + "개이어야 합니다.";
    private static final String ERROR_OUT_OF_RANGE = "로또 번호는 " + MIN_NUMBER + "부터 " + MAX_NUMBER + " 사이의 숫자이어야 합니다.";
    private static final String ERROR_DUPLICATES = "로또 번호에 중복된 숫자가 있습니다.";
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateRange(numbers);
        validateDuplicates(numbers);
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ERROR_PREFIX + ERROR_INVALID_SIZE);
        }
    }

    private void validateRange(List<Integer> numbers) {
        for (int number : numbers) {
            if (number < MIN_NUMBER || number > MAX_NUMBER) {
                throw new IllegalArgumentException(ERROR_PREFIX + ERROR_OUT_OF_RANGE);
            }
        }
    }

    private void validateDuplicates(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException(ERROR_PREFIX + ERROR_DUPLICATES);
        }
    }

    private List<Integer> sortAndMakeReadOnly(List<Integer> numbers) {
        List<Integer> sortedNumbers = new ArrayList<>(numbers);
        Collections.sort(sortedNumbers);
        return Collections.unmodifiableList(sortedNumbers);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }

    public int countMatch(List<Integer> otherNumbers) {
        int matchCount = 0;
        for (int number : numbers) {
            if (otherNumbers.contains(number)) {
                matchCount++;
            }
        }
        return matchCount;
    }

}
