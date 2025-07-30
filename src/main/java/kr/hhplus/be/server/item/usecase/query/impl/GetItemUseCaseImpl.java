package kr.hhplus.be.server.item.usecase.query.impl;

import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import kr.hhplus.be.server.item.domain.model.Item;
import kr.hhplus.be.server.item.domain.repository.ItemRepository;
import kr.hhplus.be.server.item.usecase.query.GetItemUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetItemUseCaseImpl implements GetItemUseCase {

    private final ItemRepository repository;

    public GetItemUseCaseImpl(ItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public Item getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.ITEM_NOT_FOUND));
    }

    @Override
    public List<Item> findAll() {
        return repository.findAll();
    }
}
