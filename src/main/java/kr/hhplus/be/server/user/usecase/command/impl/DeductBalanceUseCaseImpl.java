package kr.hhplus.be.server.user.usecase.command.impl;

import kr.hhplus.be.server.common.*;
import kr.hhplus.be.server.user.domain.model.User;
import kr.hhplus.be.server.user.domain.repository.UserRepository;
import kr.hhplus.be.server.user.usecase.command.DeductBalanceUseCase;
import org.springframework.stereotype.Service;

@Service
public class DeductBalanceUseCaseImpl implements DeductBalanceUseCase {

    private final UserRepository repository;

    public DeductBalanceUseCaseImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void deduct(Long userId, int amount) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_INVALID));

        user.deduct(amount);
        repository.save(user);
    }
}
