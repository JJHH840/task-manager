package com.enterprise.taskmanager.domain.comment.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enterprise.taskmanager.domain.comment.dto.request.CommentCreateRequest;
import com.enterprise.taskmanager.domain.comment.dto.response.CommentResponse;
import com.enterprise.taskmanager.domain.comment.dto.response.CommentWithTaskResponse;
import com.enterprise.taskmanager.domain.comment.entity.Comment;
import com.enterprise.taskmanager.domain.comment.repository.CommentRepository;
import com.enterprise.taskmanager.domain.task.entity.Task;
import com.enterprise.taskmanager.domain.task.repository.TaskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository; // 태스트 존재 여부 확인용

    // 댓글 작성
    @Transactional
    public CommentResponse createComment(Long taskId, CommentCreateRequest request) {
        Task task = taskRepository
                .findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 태스크 입니다. id=" + taskId));

        Comment comment = new Comment(request.getContent(), request.getAuthor(), task);
        Comment savedComment = commentRepository.save(comment);

        return CommentResponse.from(savedComment);
    }

    // 특정 태스크에 달린 댓글 목록 조회
    public List<CommentResponse> getCommentsByTaskId(Long taskId) {
        // 태스크가 존재하는지 먼저 확인
        if (!taskRepository.existsById(taskId)) {
            throw new IllegalArgumentException("존재하지 않는 태스크입니다. id=" + taskId);
        }

        return commentRepository.findByTaskId(taskId).stream()
                .map(CommentResponse::from)
                .toList();
    }

    // 전체 댓글을 태스크 정보와 함께 조회
    public List<CommentWithTaskResponse> getAllCommentWithTask() {
        return commentRepository.findAllWithTask().stream()
                .map(CommentWithTaskResponse::from)
                .toList();
    }

}
