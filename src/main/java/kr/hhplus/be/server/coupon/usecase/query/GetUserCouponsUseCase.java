package kr.hhplus.be.server.coupon.usecase.query;

import kr.hhplus.be.server.coupon.domain.model.UserCoupon;

import java.util.List;

public interface GetUserCouponsUseCase {
    List<UserCoupon> getUserCoupons(Long userId);
}
