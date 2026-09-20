package com.enterprise.taskmanager.domain.comment.dto.response;

import java.time.LocalDateTime;

import com.enterprise.taskmanager.domain.comment.entity.Comment;

import lombok.Getter;

@Getter
public class CommentResponse {
    private final Long id;
    private final String content;
    private final String author;
    private final LocalDateTime createdAt;

    public CommentResponse(Long id, String content, String author, LocalDateTime createdAt) {
        this.id = id;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
    }

    public static CommentResponse from(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getContent(),
                comment.getAuthor(),
                comment.getCreatedAt());
    }
}
