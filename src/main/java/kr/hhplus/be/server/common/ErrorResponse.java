package kr.hhplus.be.server.common;

import lombok.Getter;

import java.util.List;

@Getter
public class ErrorResponse {
    private final String code;     // "POINT_NOT_ENOUGH"
    private final String message;  // "포인트가 부족합니다."
    private final List<String> errors;

    public ErrorResponse(String code, String message, List<String> errors) {
        this.code = code;
        this.message = message;
        this.errors = errors;
    }
}