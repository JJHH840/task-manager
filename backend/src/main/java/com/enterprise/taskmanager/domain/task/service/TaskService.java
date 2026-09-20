package com.enterprise.taskmanager.domain.task.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enterprise.taskmanager.domain.task.dto.request.TaskCreateRequest;
import com.enterprise.taskmanager.domain.task.dto.request.TaskStatusUpdateRequest;
import com.enterprise.taskmanager.domain.task.dto.request.TaskUpdateRequest;
import com.enterprise.taskmanager.domain.task.dto.response.TaskResponse;
import com.enterprise.taskmanager.domain.task.entity.Task;
import com.enterprise.taskmanager.domain.task.repository.TaskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // final이 붙은 필드의 생성자를 롬복이 자동으로 만들어줌 (의존성 주입)
@Transactional(readOnly = true) // 실무 팁: 기본적으로 읽기 전용으로 두어 성능 최적화
public class TaskService {
    private final TaskRepository taskRepository;

    // 데이터를 저장하거나 변경할 때는 readOnly = false (기본값)를 명시해야 함
    @Transactional
    public TaskResponse createTask(TaskCreateRequest request) {
        Task task = new Task(request.getTitle(), request.getDescription());
        Task savedTask = taskRepository.save(task);
        return TaskResponse.from(savedTask);
    }

    public TaskResponse getTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 태스크입니다. id=" + id));
        return TaskResponse.from(task);
    }

    // 전체 목록 조회 (읽기 전용 트랜잭션 자동 적용)
    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(TaskResponse::from)
                .toList();
    }

    @Transactional // 데이터 수정이므로 트랜잭션 필수
    public TaskResponse updateTask(Long id, TaskUpdateRequest request) {
        // 1. DB에서 수정할 엔티티를 찾아옴
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 태스크입니다. id=" + id));
        // 2. 엔티티의 비즈니스 메서드를 호출하여 값을 변경(save 금지)
        task.update(request.getTitle(), request.getDescription());
        // 3. 반환할 DTO로 변환
        return TaskResponse.from(task);
    }

    @Transactional
    public void deleteTask(Long id) {
        // 먼저 지우려는 데이터가 진짜 있는지 확인부터 하는 것이 방어적 프로그래밍!
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 태스크입니다. id" + id));

        taskRepository.delete(task);
    }

    @Transactional
    public TaskResponse changeTaskStatus(Long id, TaskStatusUpdateRequest request) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 태스크입니다. id=" + id));

        // 엔티티의 상태 변경 메서드 호출 (더티 체킹으로 자동 UPDATE)
        task.changeStatus(request.getStatus());

        return TaskResponse.from(task);
    }
}
