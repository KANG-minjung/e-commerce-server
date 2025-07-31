package kr.hhplus.be.server.item.adapter.repository;

import kr.hhplus.be.server.item.domain.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaItemEntityRepository extends JpaRepository<Item, Long> {
    // 기본 메서드 제공됨: findById, findAll, save, delete 등
}