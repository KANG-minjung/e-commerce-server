package kr.hhplus.be.server.user.usecase.command;

public interface DeductBalanceUseCase {
    void deduct(Long userId, int amount);
}
