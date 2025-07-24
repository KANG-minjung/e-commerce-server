package kr.hhplus.be.server.user.adapter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.hhplus.be.server.user.domain.model.User;
import kr.hhplus.be.server.user.facade.UserFacade;
import kr.hhplus.be.server.user.usecase.dto.*;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UserDto> create(@RequestParam String name) {
        User user = userFacade.createUser(name);
        return ResponseEntity.ok(new UserDto(user.getId(), user.getUserNm(), user.getBalance()));
    }

    @PostMapping("/{id}/charge")
    @Operation(summary = "사용자 잔액 충전")
    public ResponseEntity<Void> charge(@PathVariable Long id, @RequestBody ChargeRequest req) {
        userFacade.charge(id, req.amount());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/balance")
    @Operation(summary = "사용자 잔액 조회")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        User user = userFacade.getUser(id);
        return ResponseEntity.ok(new UserDto(user.getId(), user.getUserNm(), user.getBalance()));
    }
}
