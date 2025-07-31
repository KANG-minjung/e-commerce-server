package kr.hhplus.be.server.payment.usecase.command;

import kr.hhplus.be.server.payment.domain.model.Payment;
import kr.hhplus.be.server.payment.usecase.dto.PaymentRequest;
import kr.hhplus.be.server.payment.usecase.dto.PaymentResponse;

public interface ProcessPaymentUseCase {
    PaymentResponse save(PaymentRequest payment);
    Payment execute(Long orderId, Long userId, Long couponId, int payAmount);
}
