package kr.hhplus.be.server.coupon.adapter.repository;

import kr.hhplus.be.server.coupon.domain.model.Coupon;
import kr.hhplus.be.server.coupon.domain.repository.CouponRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryCouponRepository implements CouponRepository {

    private final Map<Long, Coupon> store = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public Optional<Coupon> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Coupon save(Coupon coupon) {
        Long id = coupon.getId() != null ? coupon.getId() : idGenerator.getAndIncrement();
        Coupon saved = new Coupon(id,
                coupon.getName(),
                coupon.getType(),
                coupon.getDiscountRate(),
                coupon.getMaxIssueCount(),
                coupon.getCurrentIssueCount()
        );
        store.put(id, saved);
        return saved;
    }
}
