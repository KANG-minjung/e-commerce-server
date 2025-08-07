package kr.hhplus.be.server.user.usecase.command.impl;

import kr.hhplus.be.server.common.*;
import kr.hhplus.be.server.user.domain.model.User;
import kr.hhplus.be.server.user.domain.repository.UserRepository;
import kr.hhplus.be.server.user.usecase.command.ChargeBalanceUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChargeBalanceUseCaseImpl implements ChargeBalanceUseCase {

    private final UserRepository repository;

    @Override
    public void charge(Long userId, int amount) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_INVALID));

        user.charge(amount);
        repository.save(user);
    }
}
