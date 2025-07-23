package kr.hhplus.be.server.item.usecase;

import kr.hhplus.be.server.item.domain.model.Item;
import kr.hhplus.be.server.item.usecase.dto.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemFacade {
    private final ItemUseCase itemUseCase;

    public ItemFacade(ItemUseCase itemUseCase) {
        this.itemUseCase = itemUseCase;
    }

    public Long create(CreateItemRequest request) {
        return itemUseCase.create(request.itemNm(), request.price(), request.itemCnt()).getId();
    }

    public ItemResponse get(Long id) {
        Item item = itemUseCase.find(id);
        return toResponse(item);
    }

    public List<ItemResponse> getAll() {
        return itemUseCase.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    private ItemResponse toResponse(Item item) {
        return new ItemResponse(
                item.getId(),
                item.getItemNm(),
                item.getPrice(),
                item.getItemCnt(),
                item.getStatus()  // 여기서 상태 계산
        );
    }
}
