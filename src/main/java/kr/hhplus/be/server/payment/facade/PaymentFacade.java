package kr.hhplus.be.server.payment.facade;

import kr.hhplus.be.server.coupon.usecase.command.UseCouponUseCase;
import kr.hhplus.be.server.order.domain.model.Order;
import kr.hhplus.be.server.order.usecase.command.MarkOrderAsPaidUseCase;
import kr.hhplus.be.server.payment.adapter.external.ExternalPaymentReporter;
import kr.hhplus.be.server.payment.domain.model.Payment;
import kr.hhplus.be.server.payment.usecase.command.GetOrderForPaymentUseCase;
import kr.hhplus.be.server.payment.usecase.command.ProcessPaymentUseCase;
import kr.hhplus.be.server.payment.usecase.dto.PaymentRequest;
import kr.hhplus.be.server.payment.usecase.dto.PaymentResponse;
import kr.hhplus.be.server.user.usecase.command.DeductBalanceUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PaymentFacade {

    private final GetOrderForPaymentUseCase getOrderUseCase;
    private final UseCouponUseCase useCouponUseCase;
    private final DeductBalanceUseCase deductBalanceUseCase;
    private final MarkOrderAsPaidUseCase markOrderAsPaidUseCase;
    private final ProcessPaymentUseCase processPaymentUseCase;
    private final ExternalPaymentReporter externalPaymentReporter;

    @Transactional
    public PaymentResponse pay(PaymentRequest request) {
        Order order = getOrderUseCase.execute(request.orderId());

        int discount = useCouponUseCase.executeIfPresent(request.couponId(), order.getTotalPrice());
        int payAmount = order.getTotalPrice() - discount;

        deductBalanceUseCase.deduct(request.userId(), payAmount);
        markOrderAsPaidUseCase.execute(order);

        Payment payment = processPaymentUseCase.execute(
                request.orderId(),
                request.userId(),
                request.couponId(),
                payAmount
        );

        externalPaymentReporter.send(payment);

        return PaymentResponse.from(payment);
    }
}