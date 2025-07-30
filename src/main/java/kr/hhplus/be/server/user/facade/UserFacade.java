package kr.hhplus.be.server.user.facade;

import kr.hhplus.be.server.user.domain.model.User;
import kr.hhplus.be.server.user.usecase.command.ChargeBalanceUseCase;
import kr.hhplus.be.server.user.usecase.command.CreateUserUseCase;
import kr.hhplus.be.server.user.usecase.command.DeductBalanceUseCase;
import kr.hhplus.be.server.user.usecase.dto.BalanceResponse;
import kr.hhplus.be.server.user.usecase.dto.ChargeRequest;
import kr.hhplus.be.server.user.usecase.dto.CreateUserRequest;
import kr.hhplus.be.server.user.usecase.query.GetUserUseCase;
import org.springframework.stereotype.Component;

@Component
public class UserFacade {

    private final CreateUserUseCase createUserUseCase;
    private final ChargeBalanceUseCase chargeBalanceUseCase;
    private final DeductBalanceUseCase deductBalanceUseCase;
    private final GetUserUseCase getUserUseCase;

    public UserFacade(CreateUserUseCase createUserUseCase,
                      ChargeBalanceUseCase chargeBalanceUseCase,
                      DeductBalanceUseCase deductBalanceUseCase,
                      GetUserUseCase getUserUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.chargeBalanceUseCase = chargeBalanceUseCase;
        this.deductBalanceUseCase = deductBalanceUseCase;
        this.getUserUseCase = getUserUseCase;
    }

    public User createUser(String name) {
        return createUserUseCase.create(name);
    }

    public void charge(Long userId, int amount) {
        chargeBalanceUseCase.charge(userId, amount);
    }

    public void deduct(Long userId, int amount) {
        deductBalanceUseCase.deduct(userId, amount);
    }

    public User getUser(Long userId) {
        return getUserUseCase.getById(userId);
    }
}
