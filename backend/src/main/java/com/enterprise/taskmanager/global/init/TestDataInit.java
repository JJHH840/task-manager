package com.enterprise.taskmanager.global.init;

import com.enterprise.taskmanager.domain.comment.entity.Comment;
import com.enterprise.taskmanager.domain.comment.repository.CommentRepository;
import com.enterprise.taskmanager.domain.task.entity.Task;
import com.enterprise.taskmanager.domain.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestDataInit implements CommandLineRunner {
    private final TaskRepository taskRepository;
    private final CommentRepository commentRepository;

    @Override
    public void run(String... args) {
        System.out.println("====== [테스트 더미 데이터 자동 생성 시작] ======");
        // 태스크 3개 생성
        for (int i = 1; i <= 3; i++) {
            Task task = taskRepository.save(
                    new Task("스프링 실무 마스터 태스크 " + i, "태스크 " + i + "번에 대한 상세 업무 설명입니다."));
            // 각 태스크마다 댓글 3개씩 생성 (총 9개 댓글)
            for (int j = 1; j <= 3; j++) {
                commentRepository.save(
                        new Comment("태스크 " + i + "에 달린 " + j + "번째 업무 댓글입니다.", "작성자_" + j, task));
            }
        }
        System.out.println("====== [테스트 더미 데이터 3개 태스크 & 9개 댓글 생성 완료] ======");
    }
}
