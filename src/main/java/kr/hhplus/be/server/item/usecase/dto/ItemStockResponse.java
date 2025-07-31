package kr.hhplus.be.server.item.usecase.dto;

import kr.hhplus.be.server.item.domain.model.ItemStock;

public record ItemStockResponse(
        Long id,
        Long itemOptionId,
        int quantity
) {
    public static ItemStockResponse from(ItemStock stock) {
        return new ItemStockResponse(
                stock.getId(),
                stock.getItemOption().getId(),
                stock.getQuantity()
        );
    }
}