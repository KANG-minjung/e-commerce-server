package kr.hhplus.be.server.payment.usecase.dto;

public record PaymentRequest(
        Long orderId,
        Long userId,
        Long couponId // null 가능
) {}