package kr.hhplus.be.server.user.adapter.repository;

import kr.hhplus.be.server.user.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserEntityRepository extends JpaRepository<User, Long> {
}
