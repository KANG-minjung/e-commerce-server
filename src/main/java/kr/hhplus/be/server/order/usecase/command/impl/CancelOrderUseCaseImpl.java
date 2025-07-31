package kr.hhplus.be.server.order.usecase.command.impl;

import jakarta.transaction.Transactional;
import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import kr.hhplus.be.server.item.domain.repository.ItemStockRepository;
import kr.hhplus.be.server.item.usecase.command.RestoreItemStockUseCase;
import kr.hhplus.be.server.order.domain.model.Order;
import kr.hhplus.be.server.order.domain.model.OrderItem;
import kr.hhplus.be.server.order.domain.repository.OrderRepository;
import kr.hhplus.be.server.order.usecase.command.CancelOrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CancelOrderUseCaseImpl implements CancelOrderUseCase {

    private final OrderRepository orderRepository;
    private final ItemStockRepository itemStockRepository;

    @Override
    @Transactional
    public void cancel(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new BusinessException(ErrorCode.ORDER_NOT_FOUND));

        if (order.isCanceled()) {
            throw new BusinessException(ErrorCode.ALREADY_CANCELED);
        }

        // 주문 상태 변경
        order.cancel();

        // 재고 복원
        for (OrderItem item : order.getOrderItems()) {
            itemStockRepository.restore(item.getItemId(), item.getQuantity()); // itemOptionId 기준
        }

        orderRepository.save(order); // 상태 저장
    }
}