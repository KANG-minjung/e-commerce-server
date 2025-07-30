package kr.hhplus.be.server.coupon.usecase.dto;

import kr.hhplus.be.server.coupon.domain.model.CouponType;

public record CouponInfoResponse(
        Long id,
        String name,
        CouponType type,
        double discountRate,
        int issueLimit,
        int issuedCount
) {}
