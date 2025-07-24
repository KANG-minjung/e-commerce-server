package kr.hhplus.be.server.item.usecase.dto;

public record ItemResponse(
        Long id,
        String itemNm,
        int price,
        int quantity,
        String status
) {}
