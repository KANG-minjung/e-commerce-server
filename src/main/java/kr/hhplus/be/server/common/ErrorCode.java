package kr.hhplus.be.server.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // BALANCE Use
    BALANCE_INSUFFICIENT(HttpStatus.BAD_REQUEST, "잔액이 부족합니다."),
    BALANCE_USE_ZERO_OR_NEGATIVE(HttpStatus.BAD_REQUEST, "구매하려는 금액은 0보다 커야 합니다."),

    // BALANCE Charge
    INVALID_CHARGE_AMOUNT(HttpStatus.BAD_REQUEST, "충전 금액은 0보다 커야 합니다."),
    BALANCE_SAVE_EXCEEDS_TOTAL_LIMIT(HttpStatus.BAD_REQUEST, "충전 금액은 500,000을 넘을 수 없습니다."),

    // User Error
    USER_INVALID(HttpStatus.NOT_FOUND, "존재하지 않는 사용자입니다."),
    INVALID_USER_ID(HttpStatus.NOT_FOUND, "존재하지 않는 USER ID 입니다."),
    INVALID_AMOUNT(HttpStatus.BAD_REQUEST, "충전 금액이 잘못되었습니다."),
    INVALID_USER_BALANCE(HttpStatus.BAD_REQUEST, "잔액이 잘못되었습니다."),

    // Item
    ITEM_DECREASE_ZERO_OR_NEGATIVE(HttpStatus.BAD_REQUEST, "상품 구매 수량은 0보다 커야 합니다."),
    ITEM_OUT_OF_STOCK(HttpStatus.BAD_REQUEST, "재고가 부족합니다."),
    ITEM_STOCK_INSUFFICIENT(HttpStatus.BAD_REQUEST, "상품 취소 수량은 0보다 크고, 기존 재고보다 적어야 합니다."),
    ITEM_COUNT_EXCEEDS_TOTAL_LIMIT(HttpStatus.BAD_REQUEST, "재고는 최대 100개 까지만 등록할 수 있습니다."),
    ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 상품입니다."),
    INVALID_ITEM_NAME(HttpStatus.BAD_REQUEST, "상품 명이 잘못되었습니다."),
    INVALID_ITEM_PRICE(HttpStatus.BAD_REQUEST, "상품 가격이 잘못되었습니다."),
    INVALID_ITEM_QUANTITY(HttpStatus.BAD_REQUEST, "상품 수량이 잘못되었습니다."),
    INVALID_ITEM_PRICE_DECREASE_ZERO(HttpStatus.BAD_REQUEST, "상품 가격은 0보다 커야 합니다."),
    INVALID_ITEM_ID(HttpStatus.BAD_REQUEST, "존재하지 않는 상품ID 입니다."),
    INVALID_ITEM_OPTION_ID(HttpStatus.BAD_REQUEST, "유효하지 않은 옵션 ID입니다."),
    ITEM_OPTION_STOCK_INSUFFICIENT(HttpStatus.BAD_REQUEST, "재고 수량은 0 이상이어야 합니다."),
    INVALID_ITEM_STOCK(HttpStatus.BAD_REQUEST, "재고 정보가 없습니다."),
    INVALID_TOTAL_PRICE(HttpStatus.BAD_REQUEST, "총 금액이 잘못되었습니다."),

    // Coupon
    COUPON_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 쿠폰입니다."),
    COUPON_ALREADY_ISSUED(HttpStatus.BAD_REQUEST, "이미 발급된 쿠폰입니다."),
    COUPON_EXPIRED(HttpStatus.BAD_REQUEST, "만료된 쿠폰입니다."),
    COUPON_OUT_OF_STOCK(HttpStatus.BAD_REQUEST, "쿠폰이 모두 소진되었습니다."),
    COUPON_ALREADY_USED(HttpStatus.BAD_REQUEST, "사용처리된 쿠폰입니다."),
    INVALID_COUPON_DISCOUNT(HttpStatus.BAD_REQUEST, "할인율이 잘못 되었습니다."),

    // Order
    ORDER_INVALID(HttpStatus.BAD_REQUEST, "잘못된 주문입니다."),
    ORDER_ITEM_INVALID(HttpStatus.BAD_REQUEST, "잘못된 주문 제품입니다."),
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 주문입니다."),
    ALREADY_CANCELED(HttpStatus.BAD_REQUEST, "취소된 주문입니다."),
    ALREADY_PAID_ORDER(HttpStatus.BAD_REQUEST, "이미 지불이 완료된 주문입니다."),
    INVALID_ORDER_STATUS(HttpStatus.BAD_REQUEST, "주문 상태가 잘못되었습니다."),

    // Payment
    PAYMENT_FAILED(HttpStatus.BAD_REQUEST, "결제 처리 중 오류가 발생했습니다."),
    INVALID_PAY_AMOUNT(HttpStatus.BAD_REQUEST, "결제 총 금액 오류"),

    // Error
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류 발생.")
    ;

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getCode() {
        return this.name(); // "POINT_NOT_ENOUGH" 등
    }
}
