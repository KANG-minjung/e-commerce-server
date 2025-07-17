package kr.hhplus.be.server.controller.dto;

import lombok.Getter;

import java.time.LocalDateTime;

public class MockUserDto {

    public record UserDetailResponseDto(
            Long userId,
            String userNm,
            int balance,
            LocalDateTime updateDate
    ){}

    public record BalanceRequestDto(
            int balance
    ){}

    public record BalanceResponseDto(
            Long userId,
            int balance
    ){}
}
