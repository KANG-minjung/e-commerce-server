package kr.hhplus.be.server.item.usecase.dto;

import kr.hhplus.be.server.item.domain.model.ItemStatus;

public record ItemResponse(
        Long id,
        String itemNm,
        int price,
        int itmeCnt,
        ItemStatus status
) {}
