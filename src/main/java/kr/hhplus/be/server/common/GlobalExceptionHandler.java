package kr.hhplus.be.server.common;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // @Valid 실패
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        List<String> messages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.toList());

        ErrorResponse body = ErrorResponse.of(400, "요청 값이 유효하지 않습니다.", messages);
        return ResponseEntity.badRequest().body(body);
    }

    // @RequestParam, @PathVariable 등에서 유효성 실패
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex) {
        List<String> messages = ex.getConstraintViolations().stream()
                .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                .collect(Collectors.toList());

        ErrorResponse body = ErrorResponse.of(400, "입력 값이 유효하지 않습니다.", messages);
        return ResponseEntity.badRequest().body(body);
    }

    // @ModelAttribute 실패 등
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleBindException(BindException ex) {
        List<String> messages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.toList());

        ErrorResponse body = ErrorResponse.of(400, "바인딩 실패", messages);
        return ResponseEntity.badRequest().body(body);
    }

    // 비즈니스 예외 처리
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusiness(BusinessException ex) {
        ErrorCode code = ex.getErrorCode();

        ErrorResponse body = ErrorResponse.of(
                code.getStatus().value(),
                code.getMessage(),
                List.of(code.name())  // 클라이언트 코드 제공
        );

        return ResponseEntity.status(code.getStatus()).body(body);
    }

    // 그 외 모든 예외
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleServerError(Exception ex) {
        ErrorResponse body = ErrorResponse.of(
                500,
                "서버 내부 오류가 발생했습니다.",
                List.of(ex.getClass().getSimpleName())
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}
