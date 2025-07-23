package kr.hhplus.be.server.item.usecase;

import kr.hhplus.be.server.common.ErrorConstants;
import kr.hhplus.be.server.item.domain.model.Item;
import kr.hhplus.be.server.item.domain.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ItemUseCaseImpl implements ItemUseCase {

    private final ItemRepository itemRepository;

    public ItemUseCaseImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item create(String itemNm, int price, int itemCnt) {
        Item item = new Item(null, itemNm, itemCnt, price, LocalDateTime.now());
        return itemRepository.save(item);
    }

    @Override
    public Item find(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(ErrorConstants.INVALID_ITEM.message()));
    }

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public void decrease(Long itemId, int count) {
        Item item = find(itemId);
        item.itemCntDecrease(count);
        itemRepository.save(item);
    }

    @Override
    public void increase(Long itemId, int count) {
        Item item = find(itemId);
        item.itemCntIncrease(count);
        itemRepository.save(item);
    }
}
