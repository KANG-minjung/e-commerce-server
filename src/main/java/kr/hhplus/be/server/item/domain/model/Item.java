package kr.hhplus.be.server.item.domain.model;


import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import lombok.Getter;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Getter
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    private int quantity;
    private LocalDateTime updateDate;

    protected Item() {}

    public Item(String name, int price, int quantity) {
        if (name == null || name.isBlank()) {
            throw new BusinessException(ErrorCode.INVALID_ITEM_NAME);
        }
        if (price <= 0) {
            throw new BusinessException(ErrorCode.INVALID_ITEM_PRICE);
        }
        if (quantity < 0) {
            throw new BusinessException(ErrorCode.INVALID_ITEM_QUANTITY);
        }

        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.updateDate = LocalDateTime.now();
    }

    // 재고 차감
    public void decrease(int amount) {
        if (amount <= 0 || quantity < amount) {
            throw new BusinessException(ErrorCode.STOCK_INSUFFICIENT);
        }
        this.quantity -= amount;
    }

    // 재고 증가(주문 취소 시)
    public void restore(int amount) {
        if (amount <= 0) {
            throw new BusinessException(ErrorCode.INVALID_ITEM_QUANTITY);
        }
        this.quantity += amount;
    }

    public boolean isSoldOut() {
        return quantity == 0;
    }
}
