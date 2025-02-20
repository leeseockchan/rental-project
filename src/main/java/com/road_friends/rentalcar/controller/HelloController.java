package com.road_friends.rentalcar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

  @GetMapping("/hello")
  public String hello(){
    return "/hello";
  }

//  출력 확인용. 추후 삭제.
  @GetMapping("/con")
  public String layout1(){
    return "/admin/content1";
  }

  @GetMapping("/car-list")
  public String layout2(){
    return "/car/car-list";
  }

}
