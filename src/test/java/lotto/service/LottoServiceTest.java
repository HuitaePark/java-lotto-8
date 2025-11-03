package lotto.service;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import lotto.application.dto.IssuedLottoDto;
import lotto.application.dto.WinningStaticsDto;
import lotto.application.service.LottoService;
import lotto.domain.lotto.IssuedLotto;
import lotto.domain.lotto.WinningLotto;
import lotto.domain.repository.BonusNumberRepository;
import lotto.domain.repository.IssuedLottoRepository;
import lotto.domain.repository.WinningLottoRepository;
import lotto.domain.value.BonusNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoServiceTest {

    private LottoService lottoService;
    private TestIssuedLottoRepository issuedLottoRepository;
    private TestWinningLottoRepository winningLottoRepository;
    private TestBonusNumberRepository bonusNumberRepository;

    @BeforeEach
    void setUp() {
        issuedLottoRepository = new TestIssuedLottoRepository();
        winningLottoRepository = new TestWinningLottoRepository();
        bonusNumberRepository = new TestBonusNumberRepository();

        lottoService = new LottoService(
                bonusNumberRepository,
                issuedLottoRepository,
                winningLottoRepository
        );
    }

    @Test
    @DisplayName("구매 금액으로 로또 개수를 계산하고 발행한다")
    void getPurchaseCount() {
        // given
        String input = "5000";

        // when
        int count = lottoService.getPurchaseCount(input);

        // then
        assertThat(count).isEqualTo(5);
        assertThat(issuedLottoRepository.findAll()).hasSize(5);
    }

    @Test
    @DisplayName("구매 금액이 1000원 단위가 아니면 예외 발생")
    void getPurchaseCountWithInvalidAmount() {
        // given
        String input = "5500";

        // when & then
        assertThatThrownBy(() -> lottoService.getPurchaseCount(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("발행된 로또 티켓 목록을 조회한다")
    void getIssuedTicket() {
        // given
        lottoService.getPurchaseCount("3000");

        // when
        List<IssuedLottoDto> tickets = lottoService.getIssuedTicket();

        // then
        assertThat(tickets).hasSize(3);
        assertThat(tickets)
                .allSatisfy(ticket ->
                        assertThat(ticket.numbers()).hasSize(6)
                );
    }

    @Test
    @DisplayName("당첨 번호를 저장한다")
    void getWinningNumbers() {
        // given
        String input = "1,2,3,4,5,6";

        // when
        lottoService.getWinningNumbers(input);

        // then
        WinningLotto saved = winningLottoRepository.findFirst();
        assertThat(saved).isNotNull();
    }

    @Test
    @DisplayName("보너스 번호를 저장한다")
    void getBonusNumbers() {
        // given
        String input = "7";

        // when
        lottoService.getBonusNumbers(input);

        // then
        BonusNumber saved = bonusNumberRepository.findFirst();
        assertThat(saved).isNotNull();
        assertThat(saved.value()).isEqualTo(7);
    }

    @Test
    @DisplayName("당첨 통계를 계산한다")
    void processWinningNumbers() {
        // given
        issuedLottoRepository.saveAll(List.of(
                new IssuedLotto(List.of(1, 2, 3, 4, 5, 6)),
                new IssuedLotto(List.of(1, 2, 3, 4, 5, 7)),
                new IssuedLotto(List.of(10, 11, 12, 13, 14, 15))
        ));
        winningLottoRepository.save(new WinningLotto(List.of(1, 2, 3, 4, 5, 6)));
        bonusNumberRepository.save(new BonusNumber(7));

        // when
        WinningStaticsDto result = lottoService.processWinningNumbers();

        // then
        assertThat(result).isNotNull();
        assertThat(result.message()).isNotNull();
        assertThat(result.yield()).isPositive();
    }

    @Test
    @DisplayName("발행된 티켓이 없으면 빈 리스트를 반환한다")
    void getIssuedTicketWhenEmpty() {
        // when
        List<IssuedLottoDto> tickets = lottoService.getIssuedTicket();

        // then
        assertThat(tickets).isEmpty();
    }

    // ===== 테스트용 Repository 구현체 =====

    static class TestIssuedLottoRepository implements IssuedLottoRepository {
        private final List<IssuedLotto> storage = new ArrayList<>();

        @Override
        public void saveAll(List<IssuedLotto> issuedLottos) {
            storage.addAll(issuedLottos);
        }

        @Override
        public List<IssuedLotto> findAll() {
            return new ArrayList<>(storage);
        }
    }

    static class TestWinningLottoRepository implements WinningLottoRepository {
        private WinningLotto winningLotto;

        @Override
        public void save(WinningLotto winningLotto) {
            this.winningLotto = winningLotto;
        }

        @Override
        public List<WinningLotto> findAll() {
            return List.of();
        }

        @Override
        public WinningLotto findFirst() {
            return winningLotto;
        }

    }

    static class TestBonusNumberRepository implements BonusNumberRepository {
        private BonusNumber bonusNumber;

        @Override
        public void save(BonusNumber bonusNumber) {
            this.bonusNumber = bonusNumber;
        }

        @Override
        public List<BonusNumber> findAll() {
            return List.of();
        }

        @Override
        public BonusNumber findFirst() {
            return bonusNumber;
        }

    }
}