package kr.hhplus.be.server.payment.usecase.command;

import kr.hhplus.be.server.payment.domain.model.Payment;

public interface ProcessPaymentUseCase {
    Payment save(Payment payment);
}
