package lotto.application.util;

public class InputParser {


    public static int parseToInt(String input) {
        validateNumeric(input);
        return Integer.parseInt(input);
    }

    private static void validateNumeric(String input) {
        if (input.matches("\\d+")) {
            throw new IllegalArgumentException("구입금액은 숫자로 입력해야 합니다");
        }
    }
}
