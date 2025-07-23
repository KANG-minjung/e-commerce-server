package kr.hhplus.be.server.item.usecase;

import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import kr.hhplus.be.server.item.domain.model.Item;
import kr.hhplus.be.server.item.domain.repository.ItemRepository;
import kr.hhplus.be.server.item.usecase.dto.ItemResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemQueryUseCaseImpl implements ItemQueryUseCase {

    private ItemRepository repository;

    public ItemQueryUseCaseImpl(ItemRepository repository) {}

    @Override
    public Item find(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.ITEM_NOT_FOUND));
    }

    @Override
    public List<Item> findAll() {
        return repository.findAll();
    }
}
