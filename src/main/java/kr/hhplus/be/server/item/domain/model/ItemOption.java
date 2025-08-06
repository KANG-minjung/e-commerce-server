package kr.hhplus.be.server.item.domain.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "tbl_item_option")
@Getter
public class ItemOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String size;

    @Column
    private String color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @OneToOne(mappedBy = "itemOption", cascade = CascadeType.ALL, orphanRemoval = true)
    private ItemStock stock;

    protected ItemOption() {}

    public ItemOption(String size, String color) {
        this.size = size;
        this.color = color;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setStock(ItemStock stock) {
        this.stock = stock;
        stock.setItemOption(this);
    }
}