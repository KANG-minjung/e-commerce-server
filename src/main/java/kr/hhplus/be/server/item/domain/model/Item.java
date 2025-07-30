package kr.hhplus.be.server.item.domain.model;


import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import lombok.Getter;
import jakarta.persistence.*;
import java.lang.reflect.Field;
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

    //TestOnly
    public Item(Long id, String name, int quantity, int price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.updateDate = LocalDateTime.now();

        // 리플렉션으로 id 설정하는 대신 테스트 편의성 위해 허용
        try {
            Field field = Item.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(this, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
