package kr.hhplus.be.server.payment.usecase.command.impl;

import kr.hhplus.be.server.payment.domain.model.Payment;
import kr.hhplus.be.server.payment.domain.repository.PaymentRepository;
import kr.hhplus.be.server.payment.usecase.command.ProcessPaymentUseCase;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProcessPaymentUseCaseImpl implements ProcessPaymentUseCase {

    private final PaymentRepository paymentRepository;

    public ProcessPaymentUseCaseImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }
}