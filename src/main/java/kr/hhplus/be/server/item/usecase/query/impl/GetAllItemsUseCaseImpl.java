package kr.hhplus.be.server.item.usecase.query.impl;

import kr.hhplus.be.server.item.domain.repository.ItemRepository;
import kr.hhplus.be.server.item.usecase.dto.ItemDetailResponse;
import kr.hhplus.be.server.item.usecase.dto.ItemResponse;
import kr.hhplus.be.server.item.usecase.query.GetAllItemsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetAllItemsUseCaseImpl implements GetAllItemsUseCase {

    private final ItemRepository repository;

    @Override
    public List<ItemDetailResponse> getAllItems() {
        return repository.findAll().stream()
                .map(ItemDetailResponse::from)
                .toList();
    }
}
