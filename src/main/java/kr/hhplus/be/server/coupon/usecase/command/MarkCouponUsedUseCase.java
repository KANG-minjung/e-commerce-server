package kr.hhplus.be.server.coupon.usecase.command;

public interface MarkCouponUsedUseCase {
    void use(Long userId, Long couponId);
}
