package com.enterprise.taskmanager.global.common;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass // 자식 엔티티들에게 매핑 정보(컬럼)만 물려주는 부모 클래스 선언
@EntityListeners(AuditingEntityListener.class) // JPA에게 "엔티티의 생명주기를 감시해라!"라는 감시관(Listener) 붙이기
public abstract class BaseTimeEntity {

    @CreatedDate // 데이터 생성 시점의 시간을 자동으로 찰칵 찍음
    @Column(updatable = false, nullable = false) // 생성일시는 절대 UPDATE 되지 않도록 쐐기를 박음!
    private LocalDateTime createdAt;

    @LastModifiedDate // 데이터가 수정될 때마다 그 시점의 시간을 자동으로 찰칵 갱신함
    @Column(nullable = false)
    private LocalDateTime updatedAt;

}
