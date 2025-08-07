package kr.hhplus.be.server.item.usecase.query;

import kr.hhplus.be.server.item.usecase.dto.ItemDetailResponse;

public interface GetItemByIdUseCase {
    ItemDetailResponse getItemById(Long itemId);
}
