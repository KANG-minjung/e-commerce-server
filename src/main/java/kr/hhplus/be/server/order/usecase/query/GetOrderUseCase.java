package kr.hhplus.be.server.order.usecase.query;

import kr.hhplus.be.server.order.domain.model.Order;
import kr.hhplus.be.server.order.usecase.dto.OrderResponse;
import kr.hhplus.be.server.order.usecase.dto.OrderSummaryResponse;

import java.util.List;

public interface GetOrderUseCase {
    OrderResponse getById(Long id);
    List<OrderSummaryResponse> getAllByUserId(Long userId);
}
