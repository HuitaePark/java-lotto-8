package lotto.application.util;

import java.util.Arrays;
import java.util.List;
import lotto.application.error.ParsingErrorCode;

public class InputParser {
    private static final String COMMA = ",";
    private static final String ONLY_NUMBER_REGEX = "\\d+";
    private static final String LOTTO_NUMBERS_REGEX = "^\\d+(,\\d+){5}$";

    public static int parseToInt(String input) {
        validateNumeric(input);
        return Integer.parseInt(input);
    }

    public static List<Integer> parseToList(String input) {
        validateComma(input);
        return Arrays.stream(input.split(COMMA))
                .map(Integer::parseInt)
                .toList();
    }

    private static void validateNumeric(String input) {
        if (!input.matches(ONLY_NUMBER_REGEX)) {
            throw new IllegalArgumentException(ParsingErrorCode.INVALID_NUMBER_INPUT.getMessage());
        }
    }

    private static void validateComma(String input) {
        if (!input.matches(LOTTO_NUMBERS_REGEX)) {
            throw new IllegalArgumentException(ParsingErrorCode.INVALID_WINNER_INPUT.getMessage());
        }
    }
}
