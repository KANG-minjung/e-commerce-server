package kr.hhplus.be.server.item.adapter.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import kr.hhplus.be.server.item.domain.model.ItemStock;
import kr.hhplus.be.server.item.domain.repository.ItemStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ItemStockRepositoryImpl implements ItemStockRepository {

    private final JpaItemStockEntityRepository jpaRepository;

    @PersistenceContext
    private EntityManager entityManager;

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

    @Override
    @Transactional
    public void restore(Long itemOptionId, int quantity) {
        entityManager.createQuery("""
            UPDATE ItemStock s
            SET s.quantity = s.quantity + :qty
            WHERE s.itemOption.id = :optionId
        """)
                .setParameter("qty", quantity)
                .setParameter("optionId", itemOptionId)
                .executeUpdate();
    }

    @Override
    public ItemStock findByIdForUpdate(Long stockId) {
        return entityManager.createQuery("""
                SELECT s FROM ItemStock s
                WHERE s.id = :stockId
                """, ItemStock.class)
                .setParameter("stockId", stockId)
                .setLockMode(LockModeType.PESSIMISTIC_WRITE) // SELECT FOR UPDATE
                .getSingleResult();
    }
}
