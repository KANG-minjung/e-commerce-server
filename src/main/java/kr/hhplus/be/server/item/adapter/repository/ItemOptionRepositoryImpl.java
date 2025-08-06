package kr.hhplus.be.server.item.adapter.repository;

import kr.hhplus.be.server.item.domain.model.ItemOption;
import kr.hhplus.be.server.item.domain.repository.ItemOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ItemOptionRepositoryImpl implements ItemOptionRepository {

    private final JpaItemOptionEntityRepository jpaRepository;

    @Override
    public Optional<ItemOption> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<ItemOption> findByItemId(Long itemId) {
        return jpaRepository.findByItemId(itemId);
    }

    @Override
    public ItemOption save(ItemOption option) {
        return jpaRepository.save(option);
    }
}