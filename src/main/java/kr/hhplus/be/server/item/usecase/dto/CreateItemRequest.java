package kr.hhplus.be.server.item.usecase.dto;

import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;

public record CreateItemRequest(
        String name,
        int price
) {
    public void validate() {
        if (name == null || name.isBlank()) {
            throw new BusinessException(ErrorCode.INVALID_ITEM_NAME);
        }
        if (price <= 0) {
            throw new BusinessException(ErrorCode.INVALID_ITEM_PRICE_DECREASE_ZERO);
        }
    }
}
