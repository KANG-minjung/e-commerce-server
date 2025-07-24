package kr.hhplus.be.server.order.domain.model;

import jakarta.persistence.*;
import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "orders")
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private int totalPrice;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    protected Order() {}

    public Order(Long userId, List<OrderItem> orderItems) {
        if (userId == null || orderItems == null || orderItems.isEmpty()) {
            throw new BusinessException(ErrorCode.ORDER_INVALID);
        }

        this.userId = userId;
        this.createdAt = LocalDateTime.now();
        this.totalPrice = 0;

        for (OrderItem item : orderItems) {
            item.setOrder(this); // 양방향 연관관계 설정
            this.items.add(item);
            this.totalPrice += item.getTotalPrice();
        }
    }
}
