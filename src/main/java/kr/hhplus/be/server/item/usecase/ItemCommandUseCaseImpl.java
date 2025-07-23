package kr.hhplus.be.server.item.usecase;

import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import kr.hhplus.be.server.item.domain.model.Item;
import kr.hhplus.be.server.item.domain.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ItemCommandUseCaseImpl implements ItemCommandUseCase {

    private ItemRepository repository;

    public ItemCommandUseCaseImpl(ItemRepository repository) {}

    @Override
    public Item create(String name, int price, int itemCnt) {
        return repository.save(new Item(null, name, itemCnt, price, LocalDateTime.now()));
    }

    @Override
    public void decrease(Long itemId, int itemCnt) {
        Item item = repository.findById(itemId)
                .orElseThrow(() -> new BusinessException(ErrorCode.ITEM_NOT_FOUND));
        item.itemCntDecrease(itemCnt);
        repository.save(item);
    }

    @Override
    public void restore(Long itemId, int itemCnt) {
        Item item = repository.findById(itemId)
                .orElseThrow(() -> new BusinessException(ErrorCode.ITEM_NOT_FOUND));
        item.itemCntIncrease(itemCnt);
        repository.save(item);
    }
}
