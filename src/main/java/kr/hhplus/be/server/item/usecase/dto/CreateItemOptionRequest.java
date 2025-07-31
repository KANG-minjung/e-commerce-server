package kr.hhplus.be.server.item.usecase.dto;

import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;

public record CreateItemOptionRequest(
        Long itemId,
        String size,
        String color
) {
    public void validate() {
        if (itemId == null || itemId <= 0) {
            throw new BusinessException(ErrorCode.INVALID_ITEM_ID);
        }
    }
}