package com.enterprise.taskmanager.domain.comment.controller;

import com.enterprise.taskmanager.domain.comment.dto.request.CommentCreateRequest;
import com.enterprise.taskmanager.domain.comment.dto.response.CommentResponse;
import com.enterprise.taskmanager.domain.comment.dto.response.CommentWithTaskResponse;
import com.enterprise.taskmanager.domain.comment.service.CommentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    // 댓글 등록
    @PostMapping("/api/v1/tasks/{taskId}/comments")
    public ResponseEntity<CommentResponse> createComment(
            @PathVariable("taskId") Long taskId,
            @Valid @RequestBody CommentCreateRequest request) {
        CommentResponse response = commentService.createComment(taskId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 특정 태스크의 댓글 목록 조회
    @GetMapping("/api/v1/tasks/{taskId}/comments")
    public ResponseEntity<List<CommentResponse>> getCommentsByTaskId(
            @PathVariable("taskId") Long taskId) {
        List<CommentResponse> responses = commentService.getCommentsByTaskId(taskId);
        return ResponseEntity.ok(responses);
    }

    // 전체 댓글 피드 조회
    // 주의: 클래스 레벨 URL과 다르므로 별도 매핑
    @GetMapping("/api/v1/comments")
    public ResponseEntity<List<CommentWithTaskResponse>> getAllComments() {
        List<CommentWithTaskResponse> responses = commentService.getAllCommentWithTask();
        return ResponseEntity.ok(responses);
    }
}
