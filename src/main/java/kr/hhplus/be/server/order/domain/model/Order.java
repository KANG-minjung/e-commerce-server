package kr.hhplus.be.server.order.domain.model;

import jakarta.persistence.*;
import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_order")
@Getter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "whole_price", nullable = false)
    private int totalPrice;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<OrderItem> orderItems = new ArrayList<>();

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    protected Order() {
    }

    public Order(Long userId, int totalPrice) {
        if (userId == null || userId <= 0) {
            throw new BusinessException(ErrorCode.INVALID_USER_ID);
        }
        if (totalPrice < 0) {
            throw new BusinessException(ErrorCode.INVALID_TOTAL_PRICE);
        }

        this.userId = userId;
        this.totalPrice = totalPrice;
        this.status = OrderStatus.CREATED;
        this.updateDate = LocalDateTime.now();
    }

    public void addOrderItem(OrderItem item) {
        orderItems.add(item);
        item.setOrder(this);
    }

    public void cancel() {
        if (this.isCanceled()) {
            throw new BusinessException(ErrorCode.ALREADY_CANCELED);
        }
        this.status = OrderStatus.CANCELED;
        this.updateDate = LocalDateTime.now();
    }

    public boolean isCanceled() {
        return this.status == OrderStatus.CANCELED;
    }

    public boolean isPaid() {
        return this.status == OrderStatus.PAID;
    }

    public void markPaid() {
        if (this.status == OrderStatus.CANCELED) {
            throw new BusinessException(ErrorCode.INVALID_ORDER_STATUS);
        }
        this.status = OrderStatus.PAID;
    }
}
