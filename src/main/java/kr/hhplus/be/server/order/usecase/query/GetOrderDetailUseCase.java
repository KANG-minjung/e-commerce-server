package kr.hhplus.be.server.order.usecase.query;

import kr.hhplus.be.server.order.usecase.dto.OrderResponse;

public interface GetOrderDetailUseCase {
    OrderResponse getById(Long id);
}
