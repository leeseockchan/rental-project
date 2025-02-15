package com.road_friends.rentalcar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") // API 경로 지정
                        .allowedOrigins("http://localhost:63342") // 프론트엔드 실행 도메인
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 허용할 메서드
                        .allowedHeaders("*") // 모든 헤더 허용
                        .allowCredentials(true); // 쿠키 포함 요청 허용
            }
        };
    }
}