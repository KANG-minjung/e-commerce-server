package kr.hhplus.be.server.multiThreadTest;

import kr.hhplus.be.server.item.domain.model.ItemStock;
import kr.hhplus.be.server.item.domain.repository.ItemStockRepository;
import kr.hhplus.be.server.item.usecase.command.DecreaseStockUseCase;
import kr.hhplus.be.server.user.domain.model.User;
import kr.hhplus.be.server.user.domain.repository.UserRepository;
import kr.hhplus.be.server.user.usecase.command.DeductBalanceUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class DecreasePointConcurrencyTest {

    @Autowired
    private DeductBalanceUseCase deductBalanceUseCase;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        User userPoint = new User(1L, 1000);
        userRepository.save(userPoint);
    }

    @Test
    void 동시에_포인트_차감_요청_정상처리() throws InterruptedException {
        int threadCount = 10;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                try {
                    deductBalanceUseCase.deduct(1L, 100); // 10번 100씩 차감
                } catch (Exception e) {
                    // 실패 가능성 대비 로그
                    System.out.println(e.getMessage());
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        User user = userRepository.findByIdForUpdate(1L);
        assertThat(user.getBalance()).isEqualTo(0L);
    }
}