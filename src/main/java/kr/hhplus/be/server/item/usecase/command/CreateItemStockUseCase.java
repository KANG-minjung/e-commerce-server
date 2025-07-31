package kr.hhplus.be.server.item.usecase.command;

import kr.hhplus.be.server.item.usecase.dto.CreateItemStockRequest;
import kr.hhplus.be.server.item.usecase.dto.ItemStockResponse;

public interface CreateItemStockUseCase {
    ItemStockResponse create(CreateItemStockRequest request);
}
