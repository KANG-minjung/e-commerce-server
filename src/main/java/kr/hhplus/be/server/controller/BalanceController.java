package kr.hhplus.be.server.controller;

import kr.hhplus.be.server.controller.dto.MockUserDto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Map;

@RestController
@RequestMapping("/mock/balance")
@Tag(name = "잔액 API", description = "사용자 잔액 조회 및 충전 API")
public class BalanceController {

    // 잔액 조회
    @Operation(summary = "잔액 조회", description = "사용자의 잔액을 조회합니다.")
    @GetMapping("/{userId}")
    public ResponseEntity<BalanceResponseDto> getBalance(@PathVariable Long userId){
        return ResponseEntity.ok(new BalanceResponseDto(userId, 1000));
    }

    // 잔액 충전
    @Operation(summary = "잔액 충전", description = "사용자의 잔액을 충전합니다.")
    @PostMapping("/charge")
    public ResponseEntity<BalanceResponseDto> chargeBalance(@RequestBody Map<String, Object> request) {
        Long userId = Long.valueOf(request.get("userId").toString());
        int amount = Integer.getInteger(request.get("balance").toString());

        return ResponseEntity.ok(new BalanceResponseDto(userId, amount));
    }
}