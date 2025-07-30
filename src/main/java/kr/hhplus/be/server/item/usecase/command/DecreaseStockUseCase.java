package kr.hhplus.be.server.item.usecase.command;

import kr.hhplus.be.server.item.domain.model.Item;

public interface DecreaseStockUseCase {
    void decrease(Long itemId, int itemCnt);
    void restore(Long itemId, int itemCnt);
}
