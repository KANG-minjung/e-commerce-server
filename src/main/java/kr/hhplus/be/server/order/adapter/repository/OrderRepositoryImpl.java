package kr.hhplus.be.server.order.adapter.repository;

import kr.hhplus.be.server.order.domain.model.Order;
import kr.hhplus.be.server.order.domain.repository.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaEntityRepository jpa;

    public OrderRepositoryImpl(OrderJpaEntityRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Order save(Order order) {
        return jpa.save(order);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return jpa.findById(id);
    }

    @Override
    public List<Order> findByUserId(Long userId) {
        return jpa.findByUserId(userId);
    }
}
