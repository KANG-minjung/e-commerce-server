package kr.hhplus.be.server.coupon.usecase.dto;

import kr.hhplus.be.server.coupon.domain.model.CouponStatus;

import java.time.LocalDateTime;

public record UserCouponResponse(
        Long id,
        Long userId,
        Long couponId,
        CouponStatus status,
        LocalDateTime issuedAt,
        LocalDateTime usedAt
) {}
