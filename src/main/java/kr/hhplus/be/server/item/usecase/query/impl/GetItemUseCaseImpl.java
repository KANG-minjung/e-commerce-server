package kr.hhplus.be.server.item.usecase.query.impl;

import kr.hhplus.be.server.item.domain.model.Item;
import kr.hhplus.be.server.item.domain.repository.ItemRepository;
import kr.hhplus.be.server.item.usecase.dto.ItemDetailResponse;
import kr.hhplus.be.server.item.usecase.query.GetItemUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetItemUseCaseImpl implements GetItemUseCase {

    private final ItemRepository itemRepository;

    @Override
    public List<ItemDetailResponse> getAllItems() {
        return itemRepository.findAll().stream()
                .map(ItemDetailResponse::from)
                .toList();
    }

    @Override
    public ItemDetailResponse getItemById(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));
        return ItemDetailResponse.from(item);
    }
}
