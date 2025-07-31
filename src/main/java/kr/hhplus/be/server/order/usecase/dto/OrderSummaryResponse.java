package kr.hhplus.be.server.order.usecase.dto;

import kr.hhplus.be.server.order.domain.model.Order;

import java.time.LocalDateTime;

public record OrderSummaryResponse(
        Long orderId,
        int totalPrice,
        LocalDateTime orderedAt
) {
    public static OrderSummaryResponse from(Order order) {
        return new OrderSummaryResponse(
                order.getId(),
                order.getTotalPrice(),
                order.getUpdateDate()
        );
    }
}
