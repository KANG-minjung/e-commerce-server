package kr.hhplus.be.server.coupon.domain.repository;

import kr.hhplus.be.server.coupon.domain.model.UserCoupon;

import java.util.List;
import java.util.Optional;

public interface UserCouponRepository {
    Optional<UserCoupon> findByUserIdAndCouponId(Long userId, Long couponId);
    List<UserCoupon> findByUserId(Long userId);
    UserCoupon save(UserCoupon userCoupon);
}
