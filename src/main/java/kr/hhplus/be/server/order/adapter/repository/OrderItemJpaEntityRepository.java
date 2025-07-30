package kr.hhplus.be.server.order.adapter.repository;

import kr.hhplus.be.server.order.domain.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemJpaEntityRepository extends JpaRepository<OrderItem, Long> {}
