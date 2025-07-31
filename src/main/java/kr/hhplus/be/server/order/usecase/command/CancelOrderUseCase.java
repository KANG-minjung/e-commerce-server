package kr.hhplus.be.server.order.usecase.command;

public interface CancelOrderUseCase {
    void cancel(Long orderId);
}
