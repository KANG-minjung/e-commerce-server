package kr.hhplus.be.server.coupon.adapter.repository;

import kr.hhplus.be.server.coupon.domain.model.UserCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserCouponJpaEntityRepository extends JpaRepository<UserCoupon, Long> {
    Optional<UserCoupon> findByUserIdAndCouponId(Long userId, Long couponId);
    List<UserCoupon> findByUserId(Long userId);
}
