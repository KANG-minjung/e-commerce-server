package kr.hhplus.be.server.item.usecase.command.impl;

import jakarta.transaction.Transactional;
import kr.hhplus.be.server.item.domain.model.Item;
import kr.hhplus.be.server.item.domain.repository.ItemRepository;
import kr.hhplus.be.server.item.usecase.command.CreateItemUseCase;
import kr.hhplus.be.server.item.usecase.dto.CreateItemRequest;
import kr.hhplus.be.server.item.usecase.dto.ItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateItemUseCaseImpl implements CreateItemUseCase {

    private final ItemRepository itemRepository;

    @Override
    public ItemResponse create(CreateItemRequest request) {
        request.validate();

        Item item = new Item(request.name(), request.price());
        Item saved = itemRepository.save(item);

        return ItemResponse.from(saved);
    }
}
