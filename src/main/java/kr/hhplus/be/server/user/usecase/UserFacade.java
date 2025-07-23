package kr.hhplus.be.server.user.usecase;

import kr.hhplus.be.server.user.usecase.dto.BalanceResponse;
import kr.hhplus.be.server.user.usecase.dto.ChargeRequest;
import kr.hhplus.be.server.user.usecase.dto.CreateUserRequest;
import org.springframework.stereotype.Component;

@Component
public class UserFacade {

    private final UserCommandUseCase command;
    private final UserQueryUseCase query;

    public UserFacade(UserCommandUseCase command, UserQueryUseCase query) {
        this.command = command;
        this.query = query;
    }

    public Long createUser(CreateUserRequest request) {
        return command.createUser(request.userNm()).getId();
    }

    public void charge(Long userId, ChargeRequest request) {
        command.charge(userId, request.amount());
    }

    public BalanceResponse getBalance(Long userId) {
        return new BalanceResponse(userId, query.getBalance(userId));
    }
}
