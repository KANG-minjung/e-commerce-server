package kr.hhplus.be.server.user.usecase;

import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import kr.hhplus.be.server.user.domain.model.User;
import kr.hhplus.be.server.user.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserCommandUseCaseImpl implements UserCommandUseCase {

    private final UserRepository userRepository;

    public UserCommandUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(String name) {
        return userRepository.save(new User(null, name, 0, LocalDateTime.now()));
    }

    @Override
    public void charge(Long userId, int amount) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
        user.charge(amount);
        userRepository.save(user);
    }

    @Override
    public void deduct(Long userId, int amount) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
        user.deduct(amount);
        userRepository.save(user);
    }
}
