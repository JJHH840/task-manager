package com.enterprise.taskmanager.domain.task.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enterprise.taskmanager.domain.task.dto.request.TaskCreateRequest;
import com.enterprise.taskmanager.domain.task.dto.request.TaskStatusUpdateRequest;
import com.enterprise.taskmanager.domain.task.dto.request.TaskUpdateRequest;
import com.enterprise.taskmanager.domain.task.dto.response.TaskResponse;
import com.enterprise.taskmanager.domain.task.service.TaskService;
import com.enterprise.taskmanager.global.common.response.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor // 주방장(TaskService)을 주입받기 위한 롬복 어노테이션
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/ping")
    public String ping() {
        return "pong! task-backend is alive";
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TaskResponse>> createTask(
            @Valid @RequestBody TaskCreateRequest request) {
        TaskResponse response = taskService.createTask(request);
        // 실무 표준: 데이터가 새로 생성되었을 때는 단순 200 OK가 아니라 201 Created를 반환함!
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskResponse>> getTask(@PathVariable("id") Long id) {
        TaskResponse response = taskService.getTask(id);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TaskResponse>>> getAllTasks() {
        List<TaskResponse> response = taskService.getAllTasks();
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskResponse>> updateTask(
            @PathVariable("id") Long id,
            @Valid @RequestBody TaskUpdateRequest request) {

        TaskResponse response = taskService.updateTask(id, request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<TaskResponse>> changeTaskStatus(
            @PathVariable("id") Long id,
            @Valid @RequestBody TaskStatusUpdateRequest request) {
        TaskResponse response = taskService.changeTaskStatus(id, request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);

        // 실무 표준: 삭제가 성공하면 돌려줄 본문 내용(Body)이 없으므로 204 No Content를 반환함!
        return ResponseEntity.noContent().build();
    }

}
