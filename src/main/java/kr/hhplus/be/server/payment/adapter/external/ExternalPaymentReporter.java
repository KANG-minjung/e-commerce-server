package kr.hhplus.be.server.payment.adapter.external;

import kr.hhplus.be.server.payment.domain.model.Payment;

public interface ExternalPaymentReporter {
    void send(Payment payment);
}
