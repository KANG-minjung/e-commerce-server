package kr.hhplus.be.server.coupon.usecase;

import kr.hhplus.be.server.coupon.usecase.dto.IssueCouponRequest;

public interface CouponCommandUseCase {
    void issue(IssueCouponRequest request);
    void markUsed(Long userId, Long couponId);
}
