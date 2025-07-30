package kr.hhplus.be.server.item.usecase.command;

import kr.hhplus.be.server.item.domain.model.Item;

public interface CreateItemUseCase {
    Item create(String name, int price, int quantity);
}