package kr.hhplus.be.server.payment.usecase.command.impl;

import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import kr.hhplus.be.server.order.domain.model.Order;
import kr.hhplus.be.server.order.domain.repository.OrderRepository;
import kr.hhplus.be.server.payment.usecase.command.GetOrderForPaymentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetOrderForPaymentUseCaseImpl implements GetOrderForPaymentUseCase {

    private final OrderRepository orderRepository;

    @Override
    public Order execute(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new BusinessException(ErrorCode.ORDER_NOT_FOUND));

        if (order.isPaid()) {
            throw new BusinessException(ErrorCode.ALREADY_PAID_ORDER);
        }

        return order;
    }
}
