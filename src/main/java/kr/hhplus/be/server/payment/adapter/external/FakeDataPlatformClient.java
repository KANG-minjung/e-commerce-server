package kr.hhplus.be.server.payment.adapter.external;

import kr.hhplus.be.server.payment.domain.model.Payment;

import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class FakeDataPlatformClient {

    private static final Logger log = LoggerFactory.getLogger(FakeDataPlatformClient.class);

    public void sendPaymentData(Payment payment) {
        // 실제 외부 시스템 전송 대신 로그 처리
        log.info("🛰️ 외부 전송 완료 - Payment ID: {}, Amount: {}, Time: {}",
                payment.getId(), payment.getFinalAmount(), payment.getPaidAt());
    }
}
