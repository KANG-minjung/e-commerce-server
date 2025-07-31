package kr.hhplus.be.server.order.usecase.dto;

import kr.hhplus.be.server.order.domain.model.Order;

import java.util.List;

public record OrderResponse(
        Long orderId,
        Long userId,
        int totalPrice,
        List<OrderItemResponse> items
) {
    public record OrderItemResponse(
            Long itemId,
            int quantity
    ) {}

    public static OrderResponse from(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getUserId(),
                order.getTotalPrice(),
                order.getOrderItems().stream()
                        .map(item -> new OrderItemResponse(item.getItemId(), item.getQuantity()))
                        .toList()
        );
    }
}
