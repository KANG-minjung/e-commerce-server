package kr.hhplus.be.server.item.adapter.repository;

import kr.hhplus.be.server.item.domain.model.ItemOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaItemOptionEntityRepository extends JpaRepository<ItemOption, Long> {
    List<ItemOption> findByItemId(Long itemId);
}
