package kr.hhplus.be.server.item.adapter.repository;

import kr.hhplus.be.server.item.domain.model.ItemStock;
import kr.hhplus.be.server.item.domain.repository.ItemStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ItemStockRepositoryImpl implements ItemStockRepository {

    private final JpaItemStockEntityRepository jpaRepository;

    @Override
    public Optional<ItemStock> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public Optional<ItemStock> findByItemOptionId(Long itemOptionId) {
        return jpaRepository.findByItemOptionId(itemOptionId);
    }

    @Override
    public ItemStock save(ItemStock stock) {
        return jpaRepository.save(stock);
    }
}
