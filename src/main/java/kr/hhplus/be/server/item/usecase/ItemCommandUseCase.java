package kr.hhplus.be.server.item.usecase;

import kr.hhplus.be.server.item.domain.model.Item;

public interface ItemCommandUseCase {
    Item create(String name, int price, int itemCnt);
    void decrease(Long itemId, int itemCnt);
    void restore(Long itemId, int itemCnt);
}
