package kr.hhplus.be.server.order.usecase.command.impl;

import jakarta.transaction.Transactional;
import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import kr.hhplus.be.server.item.domain.repository.ItemRepository;
import kr.hhplus.be.server.item.usecase.command.DecreaseStockUseCase;
import kr.hhplus.be.server.order.domain.model.*;
import kr.hhplus.be.server.order.domain.repository.OrderItemRepository;
import kr.hhplus.be.server.order.domain.repository.OrderRepository;
import kr.hhplus.be.server.order.usecase.command.CreateOrderUseCase;
import kr.hhplus.be.server.order.usecase.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateOrderUseCaseImpl implements CreateOrderUseCase {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ItemRepository itemRepository;
    private final DecreaseStockUseCase decreaseStockUseCase;


    @Override
    public OrderResponse create(OrderRequest request) {
        if (request.userId() == null || request.userId() <= 0) {
            throw new BusinessException(ErrorCode.INVALID_USER_ID);
        }

        List<OrderItem> orderItems = request.items().stream()
                .map(i -> {
                    var item = itemRepository.findById(i.itemId())
                            .orElseThrow(() -> new BusinessException(ErrorCode.ITEM_NOT_FOUND));
                    return new OrderItem(item.getId(), i.quantity()); // 가격은 Item 자체에서 접근 가능하면 확장 가능
                })
                .toList();

        int totalPrice = orderItems.stream()
                .mapToInt(item -> {
                    var itemData = itemRepository.findById(item.getItemId())
                            .orElseThrow(() -> new BusinessException(ErrorCode.ITEM_NOT_FOUND));
                    return itemData.getPrice() * item.getQuantity();
                })
                .sum();

        Order order = new Order(request.userId(), totalPrice);
        for (OrderItem item : orderItems) {
            order.addOrderItem(item);
            // 재고 감소
            decreaseStockUseCase.decrease(item.getId(), item.getQuantity());
        }

        Order savedOrder = orderRepository.save(order);
        orderItems.forEach(orderItemRepository::save);

        return OrderResponse.from(savedOrder);
    }
}

