package kr.hhplus.be.server.coupon.usecase;

import kr.hhplus.be.server.coupon.domain.model.Coupon;
import kr.hhplus.be.server.coupon.domain.model.UserCoupon;

import java.util.List;

public interface CouponQueryUseCase {
    List<UserCoupon> getUserCoupons(Long userId);
    Coupon getCoupon(Long couponId);
}
