package kr.hhplus.be.server.item.usecase.dto;

import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;

public record RegisterItemRequest(
        String name,
        int price,
        String size,
        String color,
        int quantity
) {
    public void validate() {
        if (name == null || name.isBlank()) {
            throw new BusinessException(ErrorCode.INVALID_ITEM_NAME);
        }
        if (price <= 0 || quantity < 0) {
            throw new BusinessException(ErrorCode.INVALID_ITEM_PRICE);
        }
    }
}