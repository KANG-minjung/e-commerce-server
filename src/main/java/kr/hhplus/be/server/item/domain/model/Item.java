package kr.hhplus.be.server.item.domain.model;


import kr.hhplus.be.server.common.ErrorConstants;

import java.time.LocalDateTime;

public class Item {
    private final Long id;
    private final String itemNm;
    private int itemCnt;
    private final int price;
    private LocalDateTime updateDate;

    public Item(Long id, String itemNm, int itemCnt, int price, LocalDateTime updateDate) {
        this.id = id;
        this.itemNm = itemNm;
        this.itemCnt = itemCnt;
        this.price = price;
        this.updateDate = updateDate;
    }

    // 상품 재고 차감
    public void itemCntDecrease(int count){
        if(count <= 0) {
            throw new IllegalArgumentException(ErrorConstants.ITEM_DECREASE_ZERO_OR_NEGATIVE.message());
        }
        if(itemCnt < count) {
            throw new IllegalStateException(ErrorConstants.ITEM_OUT_OF_STOCK.message());
        }
        this.itemCnt -= count;
        this.updateDate = LocalDateTime.now();
    }

    // 상품 재고 증가 ( 주문이 취소 되는 경우 )
    public void itemCntIncrease(int count){
        if(count > 0) {
            throw new IllegalArgumentException(ErrorConstants.ITEM_INCREASE_ZERO_OR_NEGATIVE.message());
        }

        this.itemCnt += count;
        this.updateDate = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getItemNm() { return itemNm; }
    public int getItemCnt() { return itemCnt; }
    public int getPrice() { return price; }
    public LocalDateTime getUpdateDate() { return updateDate; }

    public ItemStatus getStatus(){
        return ItemStatus.fromQuantity(this.itemCnt);
    }
}
