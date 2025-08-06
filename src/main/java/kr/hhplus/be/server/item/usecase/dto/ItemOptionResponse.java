package kr.hhplus.be.server.item.usecase.dto;

import kr.hhplus.be.server.item.domain.model.ItemOption;

public record ItemOptionResponse(
        Long optionId,
        String size,
        String color,
        Integer stockQuantity
) {
    public static ItemOptionResponse from(ItemOption option) {
        return new ItemOptionResponse(
                option.getId(),
                option.getSize(),
                option.getColor(),
                option.getStock() != null ? option.getStock().getQuantity() : 0
        );
    }
}