package com.enterprise.taskmanager.global.common.response;

import lombok.Getter;

@Getter
public class ApiResponse<T> {

    private final boolean success; // 성공 여부
    private final T data; // 성공 시 전달할 실제 데이터 (실패 시 null)
    private final ApiError error; // 실패 시 전달할 에러 정보 (성공 시 null)

    private ApiResponse(boolean success, T data, ApiError error) {
        this.success = success;
        this.data = data;
        this.error = error;
    }

    // 1. 성공 응답 생성 팩토리 메서드
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, null);
    }

    public static <T> ApiResponse<T> error(String code, String message) {
        return new ApiResponse<>(false, null, new ApiError(code, message));
    }

    // 에러 상세 정보를 담는 내부 정적 클래스
    @Getter
    public static class ApiError {
        private final String code; // 에러 코드 (예: "NOT_FOUND", "VALIDATION_ERROR")
        private final String message; // 에러 메시지

        public ApiError(String code, String message) {
            this.code = code;
            this.message = message;
        }
    }
}
