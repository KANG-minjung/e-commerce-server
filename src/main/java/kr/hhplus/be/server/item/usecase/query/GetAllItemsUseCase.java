package kr.hhplus.be.server.item.usecase.query;

import kr.hhplus.be.server.item.usecase.dto.ItemDetailResponse;

import java.util.List;

public interface GetAllItemsUseCase {
    List<ItemDetailResponse> getAllItems();
}
