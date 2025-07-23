package kr.hhplus.be.server.user.usecase;

import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import kr.hhplus.be.server.user.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserQueryUseCaseImpl implements UserQueryUseCase {

    private final UserRepository userRepository;

    public UserQueryUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public int getBalance(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND))
                .getBalance();
    }
}
