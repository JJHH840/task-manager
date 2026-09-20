package com.enterprise.taskmanager.domain.task.entity;

import com.enterprise.taskmanager.global.common.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tasks") // 실무 팁: DB 예약어(USER, ORDER, TASK 등) 충돌 방지를 위해 복수형 테이블명 권장
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 아무나 new Task()로 빈 껍데기를 못 만들게 막되, JPA 프록시는 허용
public class Task extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // MySQL, H2 표준 Auto Increment
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING) /* 숫자가 아닌 문자열("TODO") 그대로 DB에 저장 (절대 ORDINAL 쓰지 말 것) */
    @Column(nullable = false)
    private TaskStatus status;

    // 생성자: 처음 태스크가 생성될 때는 무조건 status가 TODO로 시작하도록 강제함
    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = TaskStatus.TODO; // 기본 상태 강제
    }

    public void update(String title, String description) {
        if (this.status == TaskStatus.DONE) {
            throw new IllegalStateException("이미 완료된 테스크는 수정할 수 없습니다.");
        }
        this.title = title;
        this.description = description;
    }

    // 상태 변경 메서드 (setter 대신 명확한 비즈니스 행위)
    public void changeStatus(TaskStatus newStatus) {
        this.status = newStatus;
    }
}
