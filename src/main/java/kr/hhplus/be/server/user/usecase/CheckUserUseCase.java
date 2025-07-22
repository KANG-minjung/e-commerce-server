package kr.hhplus.be.server.user.usecase;

import kr.hhplus.be.server.common.ErrorConstants;
import kr.hhplus.be.server.user.domain.model.User;
import kr.hhplus.be.server.user.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public interface CheckUserUseCase {
    User createUser(String userNm);
    User getUser(Long id);
    void charge(Long userId, int amount);
    int getBalance(Long userId);
}
