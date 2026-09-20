package com.enterprise.taskmanager.domain.comment.dto.response;

import com.enterprise.taskmanager.domain.comment.entity.Comment;

import lombok.Getter;

@Getter
public class CommentWithTaskResponse {
    private final Long commentId;
    private final String content;
    private final String author;
    private final String taskTitle; // 태스크 제목

    public CommentWithTaskResponse(Long commentId, String content, String author, String taskTitle) {
        this.commentId = commentId;
        this.content = content;
        this.author = author;
        this.taskTitle = taskTitle;
    }

    public static CommentWithTaskResponse from(Comment comment) {
        return new CommentWithTaskResponse(
                comment.getId(),
                comment.getContent(),
                comment.getAuthor(),
                comment.getTask().getTitle()); // N + 1 폭탄 터지는 부분
    }

}
