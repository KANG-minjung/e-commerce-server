package kr.hhplus.be.server.common;

public enum ErrorConstants {

    // BALANCE Use
    BALANCE_USE_EXCEEDS_BALANCE("B001", "보유 잔액보다 많은 금액은 결제할 수 없습니다."),
    BALANCE_USE_ZERO_OR_NEGATIVE("B002", "구매하려는 금액은 0보다 커야 합니다."),
    BALANCE_USE_EXCEEDS_LIMIT("B003", "최대 500,000까지 사용 가능합니다."),

    // BALANCE Charge
    BALANCE_SAVE_EXCEEDS_TOTAL_LIMIT("B011", "충전 후 보유 잔액이 500,000을 초과할 수 없습니다."),
    BALANCE_SAVE_NOT_MULTIPLE_OF_5000("B012", "충전 금액은 5,000원 단위로만 가능합니다."),
    BALANCE_SAVE_ZERO_OR_NEGATIVE("B013", "충전 금액은 0보다 커야 합니다."),

    // User Error
    INVALID_USER("U001", "사용자가 유효하지 않습니다."),

    // Item
    ITEM_DECREASE_ZERO_OR_NEGATIVE("I001", "상품 구매 수량은 0보다 커야 합니다."),
    ITEM_OUT_OF_STOCK("I002", "재고가 부족합니다."),
    ITEM_INCREASE_ZERO_OR_NEGATIVE("I003", "상품 취소 수량은 0보다 커야 합니다."),
    ITEM_COUNT_EXCEEDS_TOTAL_LIMIT("I004", "재고는 최대 100개 까지만 등록할 수 있습니다."),
    INVALID_ITEM("I005", "존재하지 않는 상품입니다.");


    private final String code;
    private final String message;

    ErrorConstants(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String code() {
        return code;
    }

    public String message() {
        return message;
    }

    @Override
    public String toString() {
        return "[" + code + "] " + message;
    }
}
