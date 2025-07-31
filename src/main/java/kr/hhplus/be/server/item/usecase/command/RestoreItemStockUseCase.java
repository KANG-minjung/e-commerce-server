package kr.hhplus.be.server.item.usecase.command;

public interface RestoreItemStockUseCase {
    void restore(Long itemOptionId, int quantity);
}
