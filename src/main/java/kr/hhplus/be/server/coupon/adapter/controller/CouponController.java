package kr.hhplus.be.server.coupon.adapter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.hhplus.be.server.coupon.domain.model.Coupon;
import kr.hhplus.be.server.coupon.facade.CouponFacade;
import kr.hhplus.be.server.coupon.usecase.dto.*;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Void> issue(@PathVariable Long userId, @RequestBody IssueCouponRequest request) {
        facade.issue(userId, request.couponId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}")
    @Operation(summary = "쿠폰 조회")
    public ResponseEntity<List<UserCouponResponse>> getUserCoupons(@PathVariable Long userId) {
        List<UserCouponResponse> result = facade.getUserCoupons(userId).stream()
                .map(uc -> new UserCouponResponse(
                        uc.getId(), uc.getUserId(), uc.getId(),
                        uc.getStatus(), uc.getIssuedDate(), uc.getUsedDate()))
                .toList();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/available")
    @Operation(summary = "발급 가능한 쿠폰 목록 조회")
    public ResponseEntity<List<CouponInfoResponse>> getAvailableCoupons() {
        List<Coupon> coupons = facade.getAvailableCoupons();
        return ResponseEntity.ok(coupons.stream()
                .map(c -> new CouponInfoResponse(
                        c.getId(), c.getCouponNm(), c.getCouponType(), c.getDiscountRate(),
                        c.getIssueLimit(), c.getIssuedCount()))
                .toList());
    }
}
