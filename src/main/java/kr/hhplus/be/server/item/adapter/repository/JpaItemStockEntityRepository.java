package kr.hhplus.be.server.item.adapter.repository;

import kr.hhplus.be.server.item.domain.model.ItemStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaItemStockEntityRepository extends JpaRepository<ItemStock, Long> {
    Optional<ItemStock> findByItemOptionId(Long itemOptionId);
    void restore(Long itemOptionId, int quantity);
}
