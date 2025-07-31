package kr.hhplus.be.server.item.usecase.dto;

import kr.hhplus.be.server.item.domain.model.Item;

import java.util.List;
import java.util.stream.Collectors;

public record ItemDetailResponse(
        Long itemId,
        String name,
        int price,
        List<ItemOptionResponse> options
) {
    public static ItemDetailResponse from(Item item) {
        return new ItemDetailResponse(
                item.getId(),
                item.getName(),
                item.getPrice(),
                item.getOptions().stream()
                        .map(ItemOptionResponse::from)
                        .collect(Collectors.toList())
        );
    }
}