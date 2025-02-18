package com.road_friends.rentalcar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

  @GetMapping("/hello")
  public String hello(){
    return "/hello";
  }

  @GetMapping("/")
  public String layout(){
    return "/layout/layout";
  }
  @GetMapping("/con")
  public String layout2(){
    return "/admin/content1";
  }
}
