package kr.hhplus.be.server.order.domain.model;

import jakarta.persistence.*;
import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import lombok.Getter;

import jakarta.persistence.*;
import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import lombok.Getter;

@Entity
@Table(name = "tbl_order_item")
@Getter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "item_id", nullable = false)
    private Long itemId;

    @Column(nullable = false)
    private int quantity;

    @Column(name = "update_date")
    private java.time.LocalDateTime updateDate;

    protected OrderItem() {
    }

    public OrderItem(Long itemId, int quantity) {
        if (itemId == null || itemId <= 0) {
            throw new BusinessException(ErrorCode.INVALID_ITEM_ID);
        }
        if (quantity <= 0) {
            throw new BusinessException(ErrorCode.INVALID_ITEM_QUANTITY);
        }

        this.itemId = itemId;
        this.quantity = quantity;
        this.updateDate = java.time.LocalDateTime.now();
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
