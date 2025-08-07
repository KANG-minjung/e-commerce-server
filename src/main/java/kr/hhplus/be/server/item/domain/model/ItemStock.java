package kr.hhplus.be.server.item.domain.model;

import jakarta.persistence.*;
import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import lombok.Getter;

@Entity
@Table(name = "item_stock")
@Getter
public class ItemStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_option_id", nullable = false)
    private ItemOption itemOption;

    @Column(nullable = false)
    private int quantity;

    protected ItemStock() {}

    public ItemStock(int quantity) {
        if (quantity < 0) {
            throw new BusinessException(ErrorCode.INVALID_ITEM_QUANTITY);
        }
        this.quantity = quantity;
    }

    public void setItemOption(ItemOption itemOption) {
        this.itemOption = itemOption;
        if (itemOption.getStock() != this) {
            itemOption.setStock(this);
        }
    }

    public void decrease(int amount) {
        if (amount <= 0 || quantity < amount) throw new BusinessException(ErrorCode.ITEM_OUT_OF_STOCK);
        this.quantity -= amount;
    }

    public void restore(int amount) {
        if (amount <= 0) throw new BusinessException(ErrorCode.ITEM_STOCK_INSUFFICIENT);
        this.quantity += amount;
    }
}