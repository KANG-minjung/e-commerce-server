package kr.hhplus.be.server.order.usecase.command.impl;

import kr.hhplus.be.server.order.domain.model.*;
import kr.hhplus.be.server.order.domain.repository.OrderRepository;
import kr.hhplus.be.server.order.usecase.command.CreateOrderUseCase;
import kr.hhplus.be.server.order.usecase.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateOrderUseCaseImpl implements CreateOrderUseCase {

    private final OrderRepository orderRepository;

    public CreateOrderUseCaseImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order create(OrderCreateCommand command) {
        List<OrderItem> orderItems = command.items().stream()
                .map(i -> new OrderItem(i.itemId(), i.price(), i.quantity()))
                .toList();

        Order order = new Order(command.userId(), orderItems);
        return orderRepository.save(order);
    }
}

