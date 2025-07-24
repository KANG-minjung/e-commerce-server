package kr.hhplus.be.server.order.domain.model;

import jakarta.persistence.*;
import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import lombok.Getter;

@Entity
@Getter
public class OrderItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long itemId;

    private int price;

    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    protected OrderItem() {}

    public OrderItem(Long itemId, int price, int quantity) {
        if (itemId == null || price <= 0 || quantity <= 0) {
            throw new BusinessException(ErrorCode.ORDER_ITEM_INVALID);
        }

        this.itemId = itemId;
        this.price = price;
        this.quantity = quantity;
    }

    public int getTotalPrice() {
        return price * quantity;
    }
}
