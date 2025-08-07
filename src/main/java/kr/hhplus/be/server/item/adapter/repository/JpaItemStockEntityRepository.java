package kr.hhplus.be.server.item.adapter.repository;

import kr.hhplus.be.server.item.domain.model.ItemStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JpaItemStockEntityRepository extends JpaRepository<ItemStock, Long> {
    Optional<ItemStock> findByItemOptionId(Long itemOptionId);
    void restore(Long itemOptionId, int quantity);

    @Modifying
    @Query("UPDATE ItemStock s SET s.quantity = s.quantity - :amount WHERE s.itemId = :itemId AND s.quantity >= :amount")
    int decreaseStock(@Param("itemId") Long itemId, @Param("amount") int amount);
}
