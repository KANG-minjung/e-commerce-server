package kr.hhplus.be.server.item.domain.repository;

import kr.hhplus.be.server.item.domain.model.Item;

import java.util.*;

public interface ItemRepository {

    Item save(Item item);
    Optional<Item> findById(Long id);
    List<Item> findAll();
}
