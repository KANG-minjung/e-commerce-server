package kr.hhplus.be.server.item.usecase.query;

import kr.hhplus.be.server.item.usecase.dto.ItemDetailResponse;

import java.util.List;

public interface GetItemUseCase {
    List<ItemDetailResponse> getAllItems();
    ItemDetailResponse getItemById(Long itemId);
}
