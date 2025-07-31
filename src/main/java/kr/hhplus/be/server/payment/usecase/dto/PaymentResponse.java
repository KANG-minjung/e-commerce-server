package kr.hhplus.be.server.payment.usecase.dto;

import kr.hhplus.be.server.payment.domain.model.Payment;

import java.time.LocalDateTime;

public record PaymentResponse(
        Long paymentId,
        Long orderId,
        Long userId,
        Long couponId,
        int payAmount,
        LocalDateTime paidAt
) {
    public static PaymentResponse from(Payment payment) {
        return new PaymentResponse(
                payment.getId(),
                payment.getOrderId(),
                payment.getUserId(),
                payment.getCouponId(),
                payment.getPayAmount(),
                payment.getUpdateDate()
        );
    }
}