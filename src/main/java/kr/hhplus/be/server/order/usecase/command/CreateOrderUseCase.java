package kr.hhplus.be.server.order.usecase.command;

import kr.hhplus.be.server.order.domain.model.Order;
import kr.hhplus.be.server.order.usecase.dto.*;

public interface CreateOrderUseCase {
    Order create(OrderCreateCommand command);
}
