package kr.hhplus.be.server.item.domain.model;

import jakarta.persistence.*;
import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_item")
@Getter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_nm", nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ItemOption> options = new ArrayList<>();

    protected Item() {}

    public Item(String name, int price) {
        if (name == null || name.isBlank()) {
            throw new BusinessException(ErrorCode.INVALID_ITEM_NAME);
        }
        if (price <= 0) {
            throw new BusinessException(ErrorCode.INVALID_ITEM_PRICE);
        }

        this.name = name;
        this.price = price;
        this.updateDate = LocalDateTime.now();
    }

    public void addOption(ItemOption option) {
        options.add(option);
        option.setItem(this);
    }
}
