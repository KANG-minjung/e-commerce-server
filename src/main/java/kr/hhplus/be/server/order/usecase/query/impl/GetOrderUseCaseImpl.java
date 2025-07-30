package kr.hhplus.be.server.order.usecase.query.impl;

import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import kr.hhplus.be.server.order.domain.model.Order;
import kr.hhplus.be.server.order.domain.repository.OrderRepository;
import kr.hhplus.be.server.order.usecase.query.GetOrderUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetOrderUseCaseImpl implements GetOrderUseCase {

    private final OrderRepository repository;

    public GetOrderUseCaseImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Order getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.ORDER_NOT_FOUND));
    }

    @Override
    public List<Order> getByUserId(Long userId) {
        return repository.findByUserId(userId);
    }
}