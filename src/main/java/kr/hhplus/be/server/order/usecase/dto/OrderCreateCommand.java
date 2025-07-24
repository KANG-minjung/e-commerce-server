package kr.hhplus.be.server.order.usecase.dto;

import java.util.List;

public record OrderCreateCommand(
        Long userId,
        List<ItemOrderLine> items
) {
    public record ItemOrderLine(Long itemId, int price, int quantity) {}
}
