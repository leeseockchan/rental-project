package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.UserDTO;
import com.road_friends.rentalcar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @GetMapping("/admin/signup")
  public String signup() {
    return "/user/signup";
  }

  @PostMapping("/admin/signup")
  public String signup(@ModelAttribute UserDTO userDTO) {

    userService.signup(userDTO);

    return "redirect:/auth/login";
  }

  @GetMapping("/admin/login")
  public String login() {
    return "/user/login";
  }

  @GetMapping("/admin/dashboard")
  public String dashboard(){
    return "/common/dashboard";
  }
}

