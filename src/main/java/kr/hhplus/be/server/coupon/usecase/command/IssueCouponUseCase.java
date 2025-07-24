package kr.hhplus.be.server.coupon.usecase.command;

import kr.hhplus.be.server.item.domain.model.Item;

public interface IssueCouponUseCase {
    void issue(Long userId, Long couponId);
}