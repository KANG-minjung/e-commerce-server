package kr.hhplus.be.server.item.adapter.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import kr.hhplus.be.server.item.domain.repository.PopularItemQueryRepository;
import kr.hhplus.be.server.item.usecase.dto.PopularItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
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
        return null;
    }

}