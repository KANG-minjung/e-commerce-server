package kr.hhplus.be.server.coupon.domain.repository;

import kr.hhplus.be.server.coupon.domain.model.Coupon;

import java.util.List;
import java.util.Optional;

public interface CouponRepository {
    Optional<Coupon> findById(Long id);
    Coupon save(Coupon coupon);
    List<Coupon> findAll();

    Coupon findByIdForUpdate(Long couponId);
}
