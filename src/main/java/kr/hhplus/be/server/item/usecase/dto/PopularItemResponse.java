package kr.hhplus.be.server.item.usecase.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PopularItemResponse {
    private final Long itemId;
    private final String itemName;
    private final Long totalSales;
    private final int currentQuantity;
}