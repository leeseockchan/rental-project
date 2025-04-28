package com.road_friends.rentalcar.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/images/notice/**")
            .addResourceLocations("file:///E:/images/notice/");

    registry.addResourceHandler("/images/car/**")
            .addResourceLocations("file:///E:/images/car/");
  }
}
