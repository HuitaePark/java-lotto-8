package lotto.application.util;

import java.util.Arrays;
import java.util.List;

public class InputParser {
    private static final String COMMA = ",";

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
        if (!input.matches("\\d+")) {
            throw new IllegalArgumentException("[ERROR] 구입금액은 숫자로 입력해야 합니다");
        }
    }

    private static void validateComma(String input) {
        if (!input.matches("^\\d+(,\\d+){5}$")) {
            throw new IllegalArgumentException("[ERROR] 숫자 6개를 쉼표로 구분해 입력해야 합니다. 예: 1,2,3,4,5,6");
        }
    }
}
