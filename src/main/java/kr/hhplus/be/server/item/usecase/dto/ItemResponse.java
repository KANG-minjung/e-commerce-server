package kr.hhplus.be.server.item.usecase.dto;

import kr.hhplus.be.server.item.domain.model.Item;

public record ItemResponse(
        Long id,
        String name,
        int price
) {
    public static ItemResponse from(Item item) {
        return new ItemResponse(
                item.getId(),
                item.getName(),
                item.getPrice()
        );
    }
}
