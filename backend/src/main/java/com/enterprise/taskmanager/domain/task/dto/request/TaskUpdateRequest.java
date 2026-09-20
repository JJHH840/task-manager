package com.enterprise.taskmanager.domain.task.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TaskUpdateRequest {

    @NotBlank(message = "수정할 제목은 필수입니다.")
    @Size(max = 100, message = "제목은 100자 이하로 입력해주세요.")
    private String title;

    @Size(max = 1000, message = "설명은 1000자 이하로 입력해주세요.")
    private String description;

    public TaskUpdateRequest(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
