package kr.hhplus.be.server.item.usecase.dto;

import kr.hhplus.be.server.item.domain.model.Item;
import kr.hhplus.be.server.item.domain.model.ItemOption;

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

    public record ItemOptionResponse(
            Long optionId,
            String color,
            String size,
            int quantity
    ) {
        public static ItemOptionResponse from(ItemOption option) {
            return new ItemOptionResponse(
                    option.getId(),
                    option.getColor(),
                    option.getSize(),
                    option.getStock().getQuantity()
            );
        }
    }
}