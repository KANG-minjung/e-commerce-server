package kr.hhplus.be.server.item.usecase.command.impl;

import kr.hhplus.be.server.item.domain.model.ItemOption;
import kr.hhplus.be.server.item.domain.model.ItemStock;
import kr.hhplus.be.server.item.domain.repository.ItemOptionRepository;
import kr.hhplus.be.server.item.domain.repository.ItemStockRepository;
import kr.hhplus.be.server.item.usecase.command.CreateItemStockUseCase;
import kr.hhplus.be.server.item.usecase.dto.CreateItemStockRequest;
import kr.hhplus.be.server.item.usecase.dto.ItemStockResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateItemStockUseCaseImpl implements CreateItemStockUseCase {

    private final ItemOptionRepository itemOptionRepository;
    private final ItemStockRepository itemStockRepository;

    @Override
    public ItemStockResponse create(CreateItemStockRequest request) {
        request.validate();

        ItemOption option = itemOptionRepository.findById(request.itemOptionId())
                .orElseThrow(() -> new IllegalArgumentException("해당 옵션이 존재하지 않습니다."));

        ItemStock stock = new ItemStock(request.quantity());
        stock.setItemOption(option);
        option.setStock(stock); // 연관관계 설정 (option → stock)

        ItemStock saved = itemStockRepository.save(stock);
        return ItemStockResponse.from(saved);
    }
}
