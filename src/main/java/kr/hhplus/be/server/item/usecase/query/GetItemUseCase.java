package kr.hhplus.be.server.item.usecase.query;

import kr.hhplus.be.server.item.domain.model.Item;

import java.util.List;

public interface GetItemUseCase {
    Item getById(Long id);
    List<Item> findAll();
}
