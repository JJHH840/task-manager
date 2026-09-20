package com.enterprise.taskmanager.domain.comment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.enterprise.taskmanager.domain.comment.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 스프링 데이터 JPA가 메서드 이름만 보고 "WHERE task_id = ?" 쿼리를 자동 생성해 줌!
    List<Comment> findByTaskId(Long taskId);

    @Query("select c from Comment c join fetch c.task")
    List<Comment> findAllWithTask();
}
