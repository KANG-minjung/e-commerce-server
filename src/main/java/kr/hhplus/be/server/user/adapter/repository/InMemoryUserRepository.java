package kr.hhplus.be.server.user.adapter.repository;

import kr.hhplus.be.server.user.domain.model.User;
import kr.hhplus.be.server.user.domain.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryUserRepository implements UserRepository {

    private final Map<Long, User> store = new ConcurrentHashMap<Long, User>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public User save(User user) {
        // User 정보를 넘겨받았을 때, User가 없으면 insert(increment)
        Long id = user.getId() != null ? user.getId() : idGenerator.getAndIncrement();
        User saved = new User(id, user.getUserNm(), user.getBalance(), LocalDateTime.now());
        store.put(id, saved);
        return saved;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
}
