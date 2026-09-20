package com.enterprise.taskmanager.domain.task.dto.request;

import com.enterprise.taskmanager.domain.task.entity.TaskStatus;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TaskStatusUpdateRequest {

    @NotNull(message = "변경할 상태 값은 필수입니다.")
    private TaskStatus status;

    public TaskStatusUpdateRequest(TaskStatus status) {
        this.status = status;
    }
}
