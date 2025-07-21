package kr.hhplus.be.server.controller.dto;

import java.time.LocalDateTime;
import java.util.List;

public class MockItemDto {

    public record ItemDetailResponseDto(
            Long itemId,
            String itemNm,
            int itemPrice,
            int itemStock
    ){}

    public record ItemResponseDto(
            List<ItemDetailResponseDto> itemList
    ){}

}
