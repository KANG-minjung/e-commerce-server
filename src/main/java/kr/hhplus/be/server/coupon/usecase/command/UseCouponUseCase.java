package kr.hhplus.be.server.coupon.usecase.command;

public interface UseCouponUseCase {
    void use(Long userId, Long couponId);
    int executeIfPresent(Long couponId, int price);
}
