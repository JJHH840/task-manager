package com.enterprise.taskmanager.domain.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentCreateRequest {

    @NotBlank(message = "댓글 내용은 필수입니다.")
    @Size(max = 500, message = "댓글은 500자 이하로 작성해주세요.")
    private String content;

    @NotBlank(message = "작성자 이름은 필수입니다.")
    @Size(max = 50, message = "작성자 이름은 50자 이하로 작성해주세요.")
    private String author;

    public CommentCreateRequest(String content, String author) {
        this.content = content;
        this.author = author;
    }
}
