package kr.hhplus.be.server.user.usecase;

import kr.hhplus.be.server.user.usecase.dto.BalanceResponse;
import kr.hhplus.be.server.user.usecase.dto.ChargeRequest;
import kr.hhplus.be.server.user.usecase.dto.CreateUserRequest;
import org.springframework.stereotype.Component;

@Component
public class UserFacade {
    private final CheckUserUseCase checkUserUseCase;

    public UserFacade(CheckUserUseCase checkUserUseCase) {
        this.checkUserUseCase = checkUserUseCase;
    }

    public Long createUser(CreateUserRequest request) {
        return checkUserUseCase.createUser(request.userNm()).getId();
    }

    public void charge(Long userId, ChargeRequest request) {
        checkUserUseCase.charge(userId, request.amount());
    }

    public BalanceResponse getBalance(Long userId) {
        int balance = checkUserUseCase.getBalance(userId);
        return new BalanceResponse(userId, balance);
    }
}
