package com.enterprise.taskmanager.domain.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.enterprise.taskmanager.domain.task.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
