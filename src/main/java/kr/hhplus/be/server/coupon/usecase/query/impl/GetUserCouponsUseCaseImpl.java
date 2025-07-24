package kr.hhplus.be.server.coupon.usecase.query.impl;

import kr.hhplus.be.server.coupon.domain.model.UserCoupon;
import kr.hhplus.be.server.coupon.domain.repository.UserCouponRepository;
import kr.hhplus.be.server.coupon.usecase.query.GetUserCouponsUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetUserCouponsUseCaseImpl implements GetUserCouponsUseCase {

    private final UserCouponRepository repository;

    public GetUserCouponsUseCaseImpl(UserCouponRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<UserCoupon> getUserCoupons(Long userId) {
        return repository.findByUserId(userId);
    }
}
