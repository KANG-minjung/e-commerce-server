package kr.hhplus.be.server.payment.usecase.command;

import kr.hhplus.be.server.order.domain.model.Order;

public interface GetOrderForPaymentUseCase {
    Order execute(Long orderId);
}
