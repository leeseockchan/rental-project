package com.road_friends.rentalcar.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/car/**")  // 웹에서 접근할 URL 패턴
                .addResourceLocations("file:E:/images/car/");  // 실제 파일이 저장된 경로
    }
}