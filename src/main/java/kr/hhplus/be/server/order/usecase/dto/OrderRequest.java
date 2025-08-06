package kr.hhplus.be.server.order.usecase.dto;

import java.util.List;

public record OrderRequest(
        Long userId,
        List<OrderItemRequest> items
) {
    public record OrderItemRequest(
            Long itemId,
            int quantity
    ) {}
}
