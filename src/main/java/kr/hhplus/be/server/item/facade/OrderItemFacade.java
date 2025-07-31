package kr.hhplus.be.server.item.facade;

import kr.hhplus.be.server.item.usecase.command.DecreaseStockUseCase;
import kr.hhplus.be.server.item.usecase.command.RestoreItemStockUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class OrderItemFacade {

    private final DecreaseStockUseCase decreaseStockUseCase;
    private final RestoreItemStockUseCase restoreItemStockUseCase;

    @Transactional
    public void orderItem(Long optionId, int quantity) {
        decreaseStockUseCase.decrease(optionId, quantity);
    }

    @Transactional
    public void rollbackItem(Long optionId, int quantity) {
        restoreItemStockUseCase.restore(optionId, quantity);
    }
}

