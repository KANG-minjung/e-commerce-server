package kr.hhplus.be.server.order.adapter.repository;

import kr.hhplus.be.server.order.domain.model.OrderItem;
import kr.hhplus.be.server.order.domain.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderItemRepositoryImpl implements OrderItemRepository {

    private final OrderItemJpaEntityRepository jpaRepository;

    @Override
    public OrderItem save(OrderItem orderItem) {
        return jpaRepository.save(orderItem);
    }
}
