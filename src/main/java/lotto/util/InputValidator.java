package lotto.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lotto.domain.Lotto;

public class InputValidator {

    public static final int LOTTO_PRICE = 1_000;
    private static final String DELIMITER = ",";

    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String ERROR_NOT_A_NUMBER = "숫자를 입력해야 합니다.";
    private static final String ERROR_INVALID_UNIT = LOTTO_PRICE + "원 단위로 입력해야 합니다.";
    private static final String ERROR_INSUFFICIENT_AMOUNT = LOTTO_PRICE + "원 이상의 금액을 입력해야 합니다.";

    public static int validatePurchaseAmount(String input) {
        int amount = parseNumeric(input);
        validateAmountPositive(amount);
        validateAmountUnit(amount);
        return amount;
    }

    private static void validateAmountPositive(int amount) {
        if (amount < LOTTO_PRICE) {
            throw new IllegalArgumentException(ERROR_PREFIX + ERROR_INSUFFICIENT_AMOUNT);
        }
    }

    private static void validateAmountUnit(int amount) {
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ERROR_PREFIX + ERROR_INVALID_UNIT);
        }
    }

    public static List<Integer> parseWinningNumbers(String input) {
        List<String> numberStrings = splitByDelimiter(input);

        if (numberStrings.size() != Lotto.LOTTO_SIZE) {
            throw new IllegalArgumentException(ERROR_PREFIX + "당첨 번호는 " + Lotto.LOTTO_SIZE + "개여야 합니다.");
        }

        return parseNumericList(numberStrings);
    }

    public static int parseBonusNumber(String input) {
        return parseNumeric(input);
    }

    private static List<String> splitByDelimiter(String input) {
        List<String> rawParts = List.of(input.split(DELIMITER, -1));
        List<String> trimmedParts = new ArrayList<>();

        for (String part : rawParts) {
            String trimmedPart = part.trim();

            if (trimmedPart.isEmpty()) {
                throw new IllegalArgumentException(ERROR_PREFIX + "입력값에 빈 항목이 있습니다. (e.g., \"1,,\")");
            }
            trimmedParts.add(trimmedPart);
        }
        return trimmedParts;
    }

    private static List<Integer> parseNumericList(List<String> numberStrings) {
        try {
            List<Integer> numbers = new ArrayList<>();

            for (String s : numberStrings) {
                int number = Integer.parseInt(s);
                numbers.add(number);
            }
            return numbers;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_PREFIX + ERROR_NOT_A_NUMBER);
        }
    }

    private static int parseNumeric(String input) {
        try {
            String trimmedInput = input.trim();
            return Integer.parseInt(trimmedInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_PREFIX + InputValidator.ERROR_NOT_A_NUMBER);
        }
    }
}

