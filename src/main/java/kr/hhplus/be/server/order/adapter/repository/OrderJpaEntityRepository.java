package kr.hhplus.be.server.order.adapter.repository;

import kr.hhplus.be.server.order.domain.model.Order;
import kr.hhplus.be.server.order.domain.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderJpaEntityRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
}
