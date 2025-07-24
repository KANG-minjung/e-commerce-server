package kr.hhplus.be.server.order.usecase.dto;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
        Long orderId,
        Long userId,
        int totalPrice,
        LocalDateTime createdAt,
        List<OrderItemResponse> items
) {
    public record OrderItemResponse(
            Long itemId,
            int price,
            int quantity
    ) {}
}
