package kr.hhplus.be.server.user.domain.repository;

import kr.hhplus.be.server.user.domain.model.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(Long id);
}
