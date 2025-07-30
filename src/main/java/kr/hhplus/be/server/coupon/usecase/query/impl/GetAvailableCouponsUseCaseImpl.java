package kr.hhplus.be.server.coupon.usecase.query.impl;

import kr.hhplus.be.server.common.BusinessException;
import kr.hhplus.be.server.common.ErrorCode;
import kr.hhplus.be.server.coupon.domain.model.Coupon;
import kr.hhplus.be.server.coupon.domain.model.UserCoupon;
import kr.hhplus.be.server.coupon.domain.repository.CouponRepository;
import kr.hhplus.be.server.coupon.domain.repository.UserCouponRepository;
import kr.hhplus.be.server.coupon.usecase.query.GetAvailableCouponsUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAvailableCouponsUseCaseImpl implements GetAvailableCouponsUseCase {

    private final CouponRepository couponRepository;

    public GetAvailableCouponsUseCaseImpl(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    @Override
    public List<Coupon> getAvailableCoupons() {
        return couponRepository.findAll().stream()
                .filter(c -> c.getIssuedCount() < c.getIssueLimit())
                .toList();
    }
}
