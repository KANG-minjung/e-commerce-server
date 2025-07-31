package kr.hhplus.be.server.item.usecase.dto;

import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;

public record CreateItemStockRequest(
        Long itemOptionId,
        int quantity
) {
    public void validate() {
        if (itemOptionId == null || itemOptionId <= 0) {
            throw new BusinessException(ErrorCode.INVALID_ITEM_OPTION_ID);
        }
        if (quantity < 0) {
            throw new BusinessException(ErrorCode.ITEM_OPTION_STOCK_INSUFFICIENT);
        }
    }
}
