package kr.hhplus.be.server.item.usecase.command.impl;

import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import kr.hhplus.be.server.item.domain.model.Item;
import kr.hhplus.be.server.item.domain.repository.ItemRepository;
import kr.hhplus.be.server.item.usecase.command.DecreaseStockUseCase;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DecreaseStockUseCaseImpl implements DecreaseStockUseCase {

    private ItemRepository repository;

    public DecreaseStockUseCaseImpl(ItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public void decrease(Long itemId, int amount) {
        Item item = repository.findById(itemId)
                .orElseThrow(() -> new BusinessException(ErrorCode.ITEM_NOT_FOUND));
        item.decrease(amount);
        repository.save(item);
    }

    @Override
    public void restore(Long itemId, int itemCnt) {
        Item item = repository.findById(itemId)
                .orElseThrow(() -> new BusinessException(ErrorCode.ITEM_NOT_FOUND));
        item.restore(itemCnt);
        repository.save(item);
    }
}
