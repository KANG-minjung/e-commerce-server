package kr.hhplus.be.server.order.usecase.query.impl;

import kr.hhplus.be.server.order.domain.repository.OrderRepository;
import kr.hhplus.be.server.order.usecase.dto.OrderSummaryResponse;
import kr.hhplus.be.server.order.usecase.query.GetOrderListUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetOrderListUseCaseImpl implements GetOrderListUseCase {

    private final OrderRepository repository;

    @Override
    public List<OrderSummaryResponse> getAllByUserId(Long userId) {
        return repository.findByUserId(userId).stream()
                .map(OrderSummaryResponse::from)
                .toList();
    }
}