package kr.hhplus.be.server.item.usecase;

import kr.hhplus.be.server.item.domain.model.Item;
import kr.hhplus.be.server.item.usecase.dto.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemFacade {

    private final ItemCommandUseCase command;
    private final ItemQueryUseCase query;


    public ItemFacade(ItemCommandUseCase command, ItemQueryUseCase query) {
        this.command = command;
        this.query = query;
    }

    public Long create(CreateItemRequest request) {
        return command.create(request.itemNm(), request.price(), request.itemCnt()).getId();
    }

    public List<ItemResponse> getAllWithStatus() {
        return query.findAll().stream()
                .map(i -> new ItemResponse(i.getId(), i.getItemNm(), i.getPrice(), i.getItemCnt(), i.getStatus()))
                .toList();
    }

    public ItemResponse getByIdWithStatus(Long id) {
        Item i = query.find(id);
        return new ItemResponse(i.getId(), i.getItemNm(), i.getPrice(), i.getItemCnt(), i.getStatus());
    }

    // 주문 실패 시 재고 복구
    public void revertStock(Long itemId, int quantity) {
        command.restore(itemId, quantity);
    }

}
