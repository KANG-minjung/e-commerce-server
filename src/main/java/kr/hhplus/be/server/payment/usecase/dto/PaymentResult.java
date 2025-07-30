package kr.hhplus.be.server.payment.usecase.dto;

import java.time.LocalDateTime;

public record PaymentResult(Long paymentId, int finalAmount, LocalDateTime paidAt) {}
