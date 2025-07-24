package kr.hhplus.be.server.order.adapter.repository;

import kr.hhplus.be.server.order.domain.model.OrderItem;
import kr.hhplus.be.server.order.domain.repository.OrderItemRepository;
import org.springframework.stereotype.Repository;

@Repository
public class OrderItemRepositoryImpl implements OrderItemRepository {

    private final OrderItemJpaEntityRepository jpa;

    public OrderItemRepositoryImpl(OrderItemJpaEntityRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public OrderItem save(OrderItem item) {
        return jpa.save(item);
    }
}
