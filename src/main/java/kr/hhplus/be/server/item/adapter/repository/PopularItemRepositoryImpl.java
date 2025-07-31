package kr.hhplus.be.server.item.adapter.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import kr.hhplus.be.server.item.domain.repository.PopularItemQueryRepository;
import kr.hhplus.be.server.item.usecase.dto.PopularItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PopularItemRepositoryImpl implements PopularItemQueryRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<PopularItemResponse> findTop5PopularItemsWithinDays(int recentDays) {
        LocalDateTime since = LocalDateTime.now().minusDays(recentDays);

        return em.createQuery("""
            SELECT new kr.hhplus.be.server.item.usecase.dto.PopularItemResponse(
                i.id, i.name, SUM(oi.quantity), i.quantity)
            FROM OrderItem oi
            JOIN Item i ON i.id = oi.itemId
            JOIN Order o ON o.id = oi.orderId
            WHERE o.createdAt >= :since
            GROUP BY i.id, i.name, i.quantity
            ORDER BY SUM(oi.quantity) DESC
    """, PopularItemResponse.class)
                .setParameter("since", since)
                .setMaxResults(5)
                .getResultList();
    }
}
