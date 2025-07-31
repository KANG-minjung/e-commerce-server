package kr.hhplus.be.server.item.adapter.repository;

import kr.hhplus.be.server.item.domain.model.Item;
import kr.hhplus.be.server.item.domain.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepository {

    private final JpaItemEntityRepository jpaRepository;

    @Override
    public Item save(Item item) {
        return jpaRepository.save(item);
    }

    @Override
    public Optional<Item> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<Item> findAll() {
        return jpaRepository.findAll();
    }
}


