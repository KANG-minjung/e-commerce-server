package kr.hhplus.be.server.order.usecase.query.impl;

import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import kr.hhplus.be.server.order.usecase.dto.OrderResponse;
import kr.hhplus.be.server.order.usecase.dto.OrderSummaryResponse;
import kr.hhplus.be.server.order.usecase.query.GetOrderDetailUseCase;
import kr.hhplus.be.server.order.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetOrderDetailUseCaseImpl implements GetOrderDetailUseCase {

    private final OrderRepository repository;

    @Override
    public OrderResponse getById(Long orderId) {
        return repository.findById(orderId)
                .map(OrderResponse::from)
                .orElseThrow(() -> new BusinessException(ErrorCode.ORDER_NOT_FOUND));
    }
}