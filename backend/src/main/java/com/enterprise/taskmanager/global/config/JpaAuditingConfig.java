package com.enterprise.taskmanager.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing // JPA 감시 기능을 활성화하는 마법의 스위치!
public class JpaAuditingConfig {
}
