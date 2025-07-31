package kr.hhplus.be.server.item.usecase.command.impl;


import jakarta.transaction.Transactional;
import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import kr.hhplus.be.server.item.domain.model.Item;
import kr.hhplus.be.server.item.domain.model.ItemOption;
import kr.hhplus.be.server.item.domain.repository.ItemOptionRepository;
import kr.hhplus.be.server.item.domain.repository.ItemRepository;
import kr.hhplus.be.server.item.usecase.command.CreateItemOptionUseCase;
import kr.hhplus.be.server.item.usecase.dto.CreateItemOptionRequest;
import kr.hhplus.be.server.item.usecase.dto.ItemOptionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateItemOptionUseCaseImpl implements CreateItemOptionUseCase {

    private final ItemRepository itemRepository;
    private final ItemOptionRepository optionRepository;

    @Override
    public ItemOptionResponse create(CreateItemOptionRequest request) {
        request.validate();

        Item item = itemRepository.findById(request.itemId())
                .orElseThrow(() -> new BusinessException(ErrorCode.ITEM_NOT_FOUND));

        ItemOption option = new ItemOption(request.size(), request.color());
        option.setItem(item);
        ItemOption saved = optionRepository.save(option);

        return ItemOptionResponse.from(saved);
    }
}