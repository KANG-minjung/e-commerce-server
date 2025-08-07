package kr.hhplus.be.server.item.usecase.command.impl;

import jakarta.transaction.Transactional;
import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import kr.hhplus.be.server.item.domain.model.ItemStock;
import kr.hhplus.be.server.item.domain.repository.ItemStockRepository;
import kr.hhplus.be.server.item.usecase.command.DecreaseStockUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class DecreaseStockUseCaseImpl implements DecreaseStockUseCase {

    private final ItemStockRepository itemStockRepository;

    @Override
    public void decrease(Long itemOptionId, int quantity) {
        ItemStock stock = itemStockRepository.findByItemOptionId(itemOptionId)
                .orElseThrow(() -> new BusinessException(ErrorCode.INVALID_ITEM_STOCK));
        stock.decrease(quantity);
        itemStockRepository.save(stock);
    }
}