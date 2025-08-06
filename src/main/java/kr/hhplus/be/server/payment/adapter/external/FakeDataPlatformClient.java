package kr.hhplus.be.server.payment.adapter.external;

import kr.hhplus.be.server.payment.domain.model.Payment;

import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class FakeDataPlatformClient implements ExternalPaymentReporter{

    private static final Logger log = LoggerFactory.getLogger(FakeDataPlatformClient.class);

    @Override
    public void send(Payment payment) {
        System.out.println("[Mock 외부 전송] paymentId = " + payment.getId() + ", amount = " + payment.getPayAmount());
    }
}
