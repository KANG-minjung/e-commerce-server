package kr.hhplus.be.server.common;

public enum ErrorConstants {

    // Point Use
    BALANCE_USE_EXCEEDS_BALANCE("P001", "보유 잔액보다 많은 금액은 결제할 수 없습니다."),
    BALANCE_USE_ZERO_OR_NEGATIVE("P002", "구매하려는 금액은 0보다 커야 합니다."),
    BALANCE_USE_EXCEEDS_LIMIT("P003", "최대 500,000까지 사용 가능합니다."),
    //BALANCE_USE_EXCEEDS_LIMIT("P003", "최대 500,000까지 사용 가능합니다."),

    // Point Charge
    BALANCE_SAVE_EXCEEDS_TOTAL_LIMIT("P011", "충전 후 보유 잔액이 500,000을 초과할 수 없습니다."),
    BALANCE_SAVE_NOT_MULTIPLE_OF_5000("P012", "충전 금액은 5,000원 단위로만 가능합니다."),
    BALANCE_SAVE_ZERO_OR_NEGATIVE("P013", "충전 금액은 0보다 커야 합니다."),

    // User Error
    INVALID_USER("U001", "사용자가 유효하지 않습니다.");

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
