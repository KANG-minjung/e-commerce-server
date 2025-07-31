package kr.hhplus.be.server.payment.domain.repository;

import kr.hhplus.be.server.payment.domain.model.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository {
    Payment save(Payment payment);
    Optional<Payment> findById(Long id);
    List<Payment> findAll();
}
