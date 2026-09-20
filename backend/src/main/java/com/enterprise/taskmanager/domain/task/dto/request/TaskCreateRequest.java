package com.enterprise.taskmanager.domain.task.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor // 스프링이 JSON 글자를 자바 객체로 조립할 때 필수적인 기본 생성자
public class TaskCreateRequest {
    @NotBlank(message = "태스크 제목은 필수입니다.")
    @Size(max = 100, message = "제목은 100자 이하로 입력해주세요.")
    private String title;

    @Size(max = 1000, message = "설명은 1000자 이하로 입력해주세요.")
    private String description;

    // 테스트나 추후 사용을 위한 생성자
    public TaskCreateRequest(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
