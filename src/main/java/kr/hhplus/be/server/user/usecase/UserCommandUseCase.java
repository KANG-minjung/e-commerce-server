package kr.hhplus.be.server.user.usecase;

import kr.hhplus.be.server.user.domain.model.User;

public interface UserCommandUseCase {
    User createUser(String name);
    void charge(Long userId, int amount);
    void deduct(Long userId, int amount);
}
