package kr.hhplus.be.server.item.usecase;

import kr.hhplus.be.server.item.domain.model.Item;

import java.util.List;

public interface ItemUseCase {
    Item create(String itemNm, int price, int itemCnt);
    Item find(Long id);
    List<Item> findAll();
    void decrease(Long itemId, int count);
    void increase(Long itemId, int count);
}
