package kr.hhplus.be.server.item.usecase.command;

import kr.hhplus.be.server.item.usecase.dto.CreateItemOptionRequest;
import kr.hhplus.be.server.item.usecase.dto.ItemOptionResponse;

public interface CreateItemOptionUseCase {
    ItemOptionResponse create(CreateItemOptionRequest request);
}
