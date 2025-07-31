package kr.hhplus.be.server.order.domain.model;

public enum OrderStatus {
    CREATED,
    PAID,
    CANCELED;

    public boolean isPaid() {
        return this == PAID;
    }

    public boolean isCanceled() {
        return this == CANCELED;
    }
}
