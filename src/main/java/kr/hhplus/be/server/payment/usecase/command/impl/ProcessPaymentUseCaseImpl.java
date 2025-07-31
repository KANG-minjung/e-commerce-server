package kr.hhplus.be.server.payment.usecase.command.impl;

import jakarta.transaction.Transactional;
import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import kr.hhplus.be.server.coupon.domain.model.Coupon;
import kr.hhplus.be.server.coupon.domain.repository.CouponRepository;
import kr.hhplus.be.server.order.domain.model.Order;
import kr.hhplus.be.server.order.domain.repository.OrderRepository;
import kr.hhplus.be.server.payment.adapter.external.ExternalPaymentReporter;
import kr.hhplus.be.server.payment.domain.model.Payment;
import kr.hhplus.be.server.payment.domain.repository.PaymentRepository;
import kr.hhplus.be.server.payment.usecase.command.ProcessPaymentUseCase;
import kr.hhplus.be.server.payment.usecase.dto.PaymentRequest;
import kr.hhplus.be.server.payment.usecase.dto.PaymentResponse;
import kr.hhplus.be.server.user.domain.model.User;
import kr.hhplus.be.server.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class ProcessPaymentUseCaseImpl implements ProcessPaymentUseCase {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CouponRepository couponRepository;
    private final ExternalPaymentReporter externalPaymentReporter;

    @Override
    public PaymentResponse save(PaymentRequest request) {
        Order order = orderRepository.findById(request.orderId())
                .orElseThrow(() -> new BusinessException(ErrorCode.ORDER_NOT_FOUND));

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_INVALID));

        int discount = 0;

        if (request.couponId() != null) {
            Coupon coupon = couponRepository.findById(request.couponId())
                    .orElseThrow(() -> new BusinessException(ErrorCode.COUPON_NOT_FOUND));

            discount = coupon.calculateDiscount(order.getTotalPrice());
        }

        int payAmount = order.getTotalPrice() - discount;

        user.decreaseBalance(payAmount);
        userRepository.save(user);

        Payment payment = new Payment(order.getId(), user.getId(), request.couponId(), payAmount);
        Payment saved = paymentRepository.save(payment);

        externalPaymentReporter.send(payment);

        return PaymentResponse.from(saved);
    }

    @Override
    public Payment execute(Long orderId, Long userId, Long couponId, int payAmount) {
        Payment payment = new Payment(orderId, userId, couponId, payAmount);
        return paymentRepository.save(payment);
    }
}