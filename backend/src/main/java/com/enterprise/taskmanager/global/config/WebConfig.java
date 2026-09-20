package com.enterprise.taskmanager.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 1. 서버의 모든 URL 경로에 대해
                .allowedOrigins(
                        "http://localhost:3000", // React 기본 포트
                        "http://localhost:5173" // Vite + React 기본 포트
                )
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS") // 2. 허용할 HTTP 메서드
                .allowedHeaders("*") // 3. 손님이 보낼 수 있는 모든 헤더 허용
                .allowCredentials(true) // 4. 추후 쿠키나 인증 토큰을 주고받을 수 있게 허용
                .maxAge(3600); // 5. 브라우저야, 정찰기(Preflight) 검사 결과는 1시간(3600초) 동안 캐싱해 둬라!
    }
}
