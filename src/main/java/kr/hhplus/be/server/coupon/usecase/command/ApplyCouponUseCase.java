package kr.hhplus.be.server.coupon.usecase.command;

public interface ApplyCouponUseCase {
    int executeIfPresent(Long couponId, int price);
}
