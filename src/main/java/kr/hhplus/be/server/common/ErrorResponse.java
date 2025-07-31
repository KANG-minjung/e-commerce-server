package kr.hhplus.be.server.common;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
        int status,
        String message,
        LocalDateTime timestamp,
        List<String> errors
) {
    public static ErrorResponse of(int status, String message, List<String> errors) {
        return new ErrorResponse(status, message, LocalDateTime.now(), errors);
    }
}