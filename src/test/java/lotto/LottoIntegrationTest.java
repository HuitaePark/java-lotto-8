package lotto;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoIntegrationTest extends NsTest {
    private static final String ERROR_MESSAGE = "[ERROR]";

    @Test
    @DisplayName("통합_예외_번호_범위_초과")
    void 예외_테스트_번호_범위_초과() {
        assertSimpleTest(() -> {
            runException("8000", "1,2,3,4,5,46", "7");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    @DisplayName("통합_예외_보너스번호_범위_초과")
    void Exception_Test_Bonus_Number_Range_Exceeded() {
        assertSimpleTest(() -> {
            runException("8000", "1,2,3,4,5,6", "46");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    @DisplayName("통합_예외_보너스번호_중복")
    void Exception_Test_BonusNumber_Duplicate() {
        assertSimpleTest(() -> {
            runException("8000", "1,2,3,4,5,6", "6");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    @DisplayName("통합_기능_최소구매")
    void Feature_Test_Minimum_Purchase() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("1000", "1,2,3,4,5,6", "7");
                    assertThat(output()).contains(
                            "1개를 구매했습니다.",
                            "총 수익률은"
                    );
                },
                List.of(8, 21, 23, 41, 42, 43)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
