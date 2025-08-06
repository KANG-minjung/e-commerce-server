package kr.hhplus.be.server.item.facade;

import kr.hhplus.be.server.item.usecase.command.CreateItemOptionUseCase;
import kr.hhplus.be.server.item.usecase.command.CreateItemStockUseCase;
import kr.hhplus.be.server.item.usecase.command.CreateItemUseCase;
import kr.hhplus.be.server.item.usecase.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ItemFacade {

    private final CreateItemUseCase createItemUseCase;
    private final CreateItemOptionUseCase createItemOptionUseCase;
    private final CreateItemStockUseCase createItemStockUseCase;

    @Transactional
    public ItemResponse registerItem(RegisterItemRequest request) {
        request.validate();

        // 1. 상품 생성
        ItemResponse item = createItemUseCase.create(new CreateItemRequest(
                request.name(),
                request.price()
        ));

        // 2. 옵션 생성
        ItemOptionResponse option = createItemOptionUseCase.create(new CreateItemOptionRequest(
                item.id(),
                request.size(),
                request.color()
        ));

        // 3. 재고 생성
        createItemStockUseCase.create(new CreateItemStockRequest(
                option.optionId(),
                request.quantity()
        ));

        return item;
    }
}
