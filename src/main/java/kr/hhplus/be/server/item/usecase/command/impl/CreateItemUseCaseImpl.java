package kr.hhplus.be.server.item.usecase.command.impl;

import kr.hhplus.be.server.item.domain.model.Item;
import kr.hhplus.be.server.item.domain.repository.ItemRepository;
import kr.hhplus.be.server.item.usecase.command.CreateItemUseCase;
import org.springframework.stereotype.Service;

@Service
public class CreateItemUseCaseImpl implements CreateItemUseCase {

    private final ItemRepository repository;

    public CreateItemUseCaseImpl(ItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public Item create(String name, int price, int quantity) {
        return repository.save(new Item(name, price, quantity));
    }
}
