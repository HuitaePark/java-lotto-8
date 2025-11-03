package lotto.service;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.application.util.InputParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class InputParserTest {

    @DisplayName("구입 금액이 숫자가 아닐경우 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1원", "1000원", "10000원"})
    void when_input_is_not_number(String input) {
        assertThatThrownBy(() -> InputParser.parseToInt(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구입 금액이 숫자일 경우 에러가 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(strings = {"10000", "2000", "30000"})
    void when_input_is_number(String input) {
        assertThatCode(() -> InputParser.parseToInt(input))
                .doesNotThrowAnyException();
    }

    @DisplayName("당첨복권이 제대로 입력되지 않을경우 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1,,,,", "3,3,4,5,5", "", " "})
    void when_input_is_not_winner(String input) {
        assertThatThrownBy(() -> InputParser.parseToList(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨복권이 제대로 입력될 경우 에러가 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5,6", "11,12,13,14,15,16", "39,40,41,42,43,44"})
    void when_input_is_winner(String input) {
        assertThatCode(() -> InputParser.parseToList(input))
                .doesNotThrowAnyException();
    }
}
