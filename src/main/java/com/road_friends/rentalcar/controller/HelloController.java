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

  @GetMapping("/car")
  public String layout2(){
    return "/car/car-list";
  }

  @GetMapping("/parking")
  public String layout3(){
    return "/parking/parking-update";
  }

  @GetMapping("/users")
  public String layout4(){
    return "/users/users-detail";
  }

  @GetMapping("/review")
  public String layout5(){
    return "/review/review-detail";
  }

}
