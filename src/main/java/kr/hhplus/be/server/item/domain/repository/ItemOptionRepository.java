package kr.hhplus.be.server.item.domain.repository;

import kr.hhplus.be.server.item.domain.model.ItemOption;

import java.util.List;
import java.util.Optional;

public interface ItemOptionRepository {
    Optional<ItemOption> findById(Long id);
    List<ItemOption> findByItemId(Long itemId);
    ItemOption save(ItemOption option);
}