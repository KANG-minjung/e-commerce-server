package kr.hhplus.be.server.coupon.usecase.dto;

import java.time.LocalDateTime;

public record IssuedCouponResponse(Long couponId, String status, LocalDateTime issuedDate, LocalDateTime usedDate) {
}
