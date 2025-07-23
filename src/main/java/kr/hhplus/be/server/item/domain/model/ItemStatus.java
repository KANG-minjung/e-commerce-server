package kr.hhplus.be.server.item.domain.model;

public enum ItemStatus {
    OUT_OF_STOCK,
    LOW_STOCK,
    IN_STOCK;

    public static ItemStatus fromQuantity(int itemCnt){
        if (itemCnt < 0) {
            return OUT_OF_STOCK;
        }
        else if (itemCnt <= 10) {
            return LOW_STOCK;
        }
        else {
            return IN_STOCK;
        }
    }
}
