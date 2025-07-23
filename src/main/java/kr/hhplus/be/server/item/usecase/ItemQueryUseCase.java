package kr.hhplus.be.server.item.usecase;

import kr.hhplus.be.server.item.domain.model.Item;

import java.util.List;

public interface ItemQueryUseCase {
    Item find(Long id);
    List<Item> findAll();
}
