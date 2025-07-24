package kr.hhplus.be.server.coupon.usecase.query;

import kr.hhplus.be.server.coupon.domain.model.Coupon;

import java.util.List;

public interface GetAvailableCouponsUseCase {
    List<Coupon> getAvailableCoupons();
}
