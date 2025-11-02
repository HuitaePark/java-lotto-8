package lotto.application.dto;

import java.util.List;
import lotto.domain.lotto.IssuedLotto;

public record IssuedLottoDto(List<Integer> numbers) {
    public static IssuedLottoDto from(IssuedLotto issuedLotto) {
        return new IssuedLottoDto(issuedLotto.getIssuedLotto());
    }
}
