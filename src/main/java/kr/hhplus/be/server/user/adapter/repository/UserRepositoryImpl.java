package kr.hhplus.be.server.user.adapter.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import kr.hhplus.be.server.user.domain.model.User;
import kr.hhplus.be.server.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserEntityRepository jpaRepository;

    private final EntityManager em;

    @Override
    public User save(User user) {
        return jpaRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public User findByIdForUpdate(Long userId){
        return em.createQuery("""
                SELECT u FROM User u
                WHERE u.id = :userId
                """, User.class)
                .setParameter("userId", userId)
                .setLockMode(LockModeType.PESSIMISTIC_WRITE) // SELECT FOR UPDATE
                .getSingleResult();
    }
}

