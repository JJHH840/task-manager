package com.enterprise.taskmanager.global.exception;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private final int status; // 예: 404
    private final String message; // 예: "존재하지 않는 태스크입니다."

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
