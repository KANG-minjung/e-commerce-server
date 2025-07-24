package kr.hhplus.be.server.coupon.usecase.dto;

public record CouponResponse(
        Long id,
        String name,
        String type,
        double discountRate,
        int maxIssueCount,
        int currentIssueCount
) {
}
