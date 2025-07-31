package kr.hhplus.be.server.item.usecase.command;

import kr.hhplus.be.server.item.usecase.dto.CreateItemRequest;
import kr.hhplus.be.server.item.usecase.dto.ItemResponse;

public interface CreateItemUseCase {
    ItemResponse create(CreateItemRequest request);
}