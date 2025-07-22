package kr.hhplus.be.server.user.usecase;

import kr.hhplus.be.server.common.ErrorConstants;
import kr.hhplus.be.server.user.domain.model.User;
import kr.hhplus.be.server.user.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CheckUserUseCaseImpl implements CheckUserUseCase {

    private final UserRepository userRepository;

    public CheckUserUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(String userNm) {
        User user = new User(null, userNm, 0, LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(ErrorConstants.INVALID_USER.message()));
    }

    @Override
    public void charge(Long userId, int amount) {
        User user = getUser(userId);
        user.charge(amount);
        userRepository.save(user);
    }

    @Override
    public int getBalance(Long userId) {
        return getUser(userId).getBalance();
    }
}
