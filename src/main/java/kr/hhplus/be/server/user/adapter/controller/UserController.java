package kr.hhplus.be.server.user.adapter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.hhplus.be.server.user.usecase.UserFacade;
import kr.hhplus.be.server.user.usecase.dto.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Tag(name = "User", description = "사용자 관련 API")
public class UserController {
    // Facade를 통해 Controller에서 직접적으로 UserUseCase를 호출하지 않도록 한다.
    private final UserFacade userFacade;

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @PostMapping
    @Operation(summary = "사용자 생성")
    public Long createUser(@RequestBody CreateUserRequest request) {
        return userFacade.createUser(request);
    }

    @PostMapping("/{id}/charge")
    @Operation(summary = "사용자 잔액 충전")
    public void charge(@PathVariable Long id, @RequestBody ChargeRequest request) {
        userFacade.charge(id, request);
    }

    @GetMapping("/{id}/balance")
    @Operation(summary = "사용자 잔액 조회")
    public BalanceResponse getBalance(@PathVariable Long id) {
        return userFacade.getBalance(id);
    }
}
