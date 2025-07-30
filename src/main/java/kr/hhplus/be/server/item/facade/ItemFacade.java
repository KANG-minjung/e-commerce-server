package kr.hhplus.be.server.item.facade;

import kr.hhplus.be.server.item.domain.model.Item;
import kr.hhplus.be.server.item.usecase.command.CreateItemUseCase;
import kr.hhplus.be.server.item.usecase.command.DecreaseStockUseCase;
import kr.hhplus.be.server.item.usecase.dto.*;
import kr.hhplus.be.server.item.usecase.query.GetItemUseCase;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemFacade {

    private final CreateItemUseCase createItemUseCase;
    private final DecreaseStockUseCase decreaseStockUseCase;
    private final GetItemUseCase getItemUseCase;

    public ItemFacade(CreateItemUseCase createItemUseCase,
                      DecreaseStockUseCase decreaseStockUseCase,
                      GetItemUseCase getItemUseCase) {
        this.createItemUseCase = createItemUseCase;
        this.decreaseStockUseCase = decreaseStockUseCase;
        this.getItemUseCase = getItemUseCase;
    }

    public Item create(String name, int price, int quantity) {
        return createItemUseCase.create(name, price, quantity);
    }

    public void decrease(Long itemId, int amount) {
        decreaseStockUseCase.decrease(itemId, amount);
    }

    public Item getById(Long id) {
        return getItemUseCase.getById(id);
    }

    public List<Item> getAll() {
        return getItemUseCase.findAll();
    }

    // 주문 실패 시 재고 복구
    public void revertStock(Long itemId, int quantity) {
        decreaseStockUseCase.restore(itemId, quantity);
    }

}
