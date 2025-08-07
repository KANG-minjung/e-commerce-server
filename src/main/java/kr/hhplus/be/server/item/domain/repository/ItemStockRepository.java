package kr.hhplus.be.server.item.domain.repository;

import kr.hhplus.be.server.item.domain.model.ItemStock;

import java.util.Optional;

public interface ItemStockRepository {
    Optional<ItemStock> findById(Long id);
    Optional<ItemStock> findByItemOptionId(Long itemOptionId);
    ItemStock save(ItemStock stock);
    void restore(Long itemOptionId, int quantity);
    ItemStock findByIdForUpdate(Long stockId);
}