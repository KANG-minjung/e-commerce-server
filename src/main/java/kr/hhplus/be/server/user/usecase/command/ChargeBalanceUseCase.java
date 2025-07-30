package kr.hhplus.be.server.user.usecase.command;

public interface ChargeBalanceUseCase {
    void charge(Long userId, int amount);
}
