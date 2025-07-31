package kr.hhplus.be.server.order.usecase.command;

import kr.hhplus.be.server.order.usecase.dto.*;

public interface CreateOrderUseCase {
    OrderResponse create(OrderRequest command);
}
