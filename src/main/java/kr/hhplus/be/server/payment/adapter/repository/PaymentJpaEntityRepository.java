package kr.hhplus.be.server.payment.adapter.repository;

import kr.hhplus.be.server.payment.domain.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentJpaEntityRepository extends JpaRepository<Payment, Long> {
}
