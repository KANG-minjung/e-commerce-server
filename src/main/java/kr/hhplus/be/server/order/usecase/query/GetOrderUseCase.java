package kr.hhplus.be.server.order.usecase.query;

import kr.hhplus.be.server.order.domain.model.Order;

import java.util.List;

public interface GetOrderUseCase {
    Order getById(Long id);
    List<Order> getByUserId(Long userId);
}
