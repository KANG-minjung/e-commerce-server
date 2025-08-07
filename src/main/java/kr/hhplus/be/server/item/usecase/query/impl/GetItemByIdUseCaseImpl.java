package kr.hhplus.be.server.item.usecase.query.impl;

import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import kr.hhplus.be.server.item.domain.model.Item;
import kr.hhplus.be.server.item.domain.repository.ItemRepository;
import kr.hhplus.be.server.item.usecase.dto.ItemDetailResponse;
import kr.hhplus.be.server.item.usecase.query.GetItemByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetItemByIdUseCaseImpl implements GetItemByIdUseCase {

    private final ItemRepository repository;

    @Override
    public ItemDetailResponse getItemById(Long itemId) {
        Item item = repository.findById(itemId)
                .orElseThrow(() -> new BusinessException(ErrorCode.ITEM_NOT_FOUND));
        return ItemDetailResponse.from(item);
    }
}
