package kr.hhplus.be.server.item.adapter.repository;

import kr.hhplus.be.server.item.domain.model.Item;
import kr.hhplus.be.server.item.domain.repository.ItemRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryItemRepository implements ItemRepository {

    private final Map<Long, Item> store = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public Item save(Item item) {
        Long id = item.getId() != null ? item.getId() : idGenerator.getAndIncrement();
        Item saved = new Item(id, item.getItemNm(), item.getItemCnt(), item.getPrice(), LocalDateTime.now());
        store.put(id, saved);
        return saved;
    }

    @Override
    public Optional<Item> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }
}