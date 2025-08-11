package kr.hhplus.be.server.multiThreadTest;

import kr.hhplus.be.server.coupon.domain.model.Coupon;
import kr.hhplus.be.server.coupon.domain.repository.CouponRepository;
import kr.hhplus.be.server.coupon.domain.repository.UserCouponRepository;
import kr.hhplus.be.server.coupon.usecase.command.IssueCouponUseCase;
import kr.hhplus.be.server.user.domain.model.User;
import kr.hhplus.be.server.user.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class CouponConcurrencyTest {

    @Autowired
    private IssueCouponUseCase issueCouponUseCase;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private UserCouponRepository userCouponRepository;

    @BeforeEach
    void setUp() {
        Coupon coupon = new Coupon("10% 할인", 5); // 수량 5개짜리
        couponRepository.save(coupon);

        for (long i = 1; i <= 10; i++) {
            User user = new User(i, "user" + i, 0);
            userRepository.save(user);
        }
    }

    @Test
    void 동시에_발급해도_5명만_성공한다() throws InterruptedException {
        int threadCount = 10;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 1; i <= threadCount; i++) {
            final long userId = i;
            executor.submit(() -> {
                try {
                    issueCouponUseCase.issue(userId, 1L);
                } catch (Exception e) {
                    System.out.println("[실패] " + e.getMessage());
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        Coupon coupon = couponRepository.findByIdForUpdate(1L);
        assertThat(coupon.getIssueLimit()).isEqualTo(5);
        assertThat(userCouponRepository.countByCoupon(coupon)).isEqualTo(5);
    }
}