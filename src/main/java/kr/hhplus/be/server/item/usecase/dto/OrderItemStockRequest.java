package kr.hhplus.be.server.item.usecase.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;

public record OrderItemStockRequest(
        @NotNull(message = "옵션 ID는 필수입니다.")
        Long itemOptionId,

        @Min(value = 1, message = "수량은 1개 이상이어야 합니다.")
        int quantity
) {}