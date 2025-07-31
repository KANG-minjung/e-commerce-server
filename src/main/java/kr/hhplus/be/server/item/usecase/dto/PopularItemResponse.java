package kr.hhplus.be.server.item.usecase.dto;

public record PopularItemResponse(Long itemId, String name, int totalSold, int remainStock) {
}
