package com.road_friends.rentalcar.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/images/notice/**") // 클라이언트에서 접근할 URL 패턴
            .addResourceLocations("file:///E:/images/notice/"); // 실제 파일 저장 경로

    // 내부 정적 리소스 폴더 (static/images/)에서 이미지 제공
    registry.addResourceHandler("/images/**")
            .addResourceLocations("classpath:/static/images/");
  }
}
