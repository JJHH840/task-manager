package com.enterprise.taskmanager.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.enterprise.taskmanager.global.common.response.ApiResponse;

@RestControllerAdvice // 모든 컨트롤러에서 발생하는 예외를 전역(Global)에서 감시하는 어노테이션
public class GlobalExceptionHandler {

    // 1. 자원을 찾지 못했을 때 (404 Not Found)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("RESOURE_NOT_FOUND", e.getMessage()));
    }

    // 2. 비즈니스 규칙 위반 (400 Bad Request)
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiResponse<Void>> handleIllegalStateException(IllegalStateException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error("INVALID_STATE", e.getMessage()));
    }

    // 3. @Valid 입력값 검증 실패 (400 Bad Request) - 제목 누락 등
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationException(MethodArgumentNotValidException e) {
        // 첫 번째 에러 메시지만 추출 (예: "태스크 제목은 필수입니다.")
        String errorMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error("VALIDATION_ERROR", errorMessage));
    }
}
