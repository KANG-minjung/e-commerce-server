package kr.hhplus.be.server.coupon.adapter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.hhplus.be.server.coupon.usecase.CouponFacade;
import kr.hhplus.be.server.coupon.usecase.dto.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coupons")
@Tag(name = "Coupons", description = "쿠폰 관련 API")
public class CouponController {
    private final CouponFacade facade;

    public CouponController(CouponFacade facade) {
        this.facade = facade;
    }

    @PostMapping("/issuance")
    @Operation(summary = "쿠폰 발행")
    public void issue(@RequestBody IssueCouponRequest request) {
        facade.issue(request);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "쿠폰 정보 조회")
    public List<IssuedCouponResponse> getUserCoupons(@PathVariable Long userId) {
        return facade.getUserCoupons(userId);
    }

    @GetMapping("/info/{couponId}")
    @Operation(summary = "쿠폰 리스트 조회")
    public CouponResponse getCouponInfo(@PathVariable Long couponId) {
        return facade.getCoupon(couponId);
    }
}
