package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.UserDto;
import com.road_friends.rentalcar.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    return "user/signup";
  }

  @PostMapping("/admin/signup")
  public String signup(@ModelAttribute UserDto userDTO) {

    userService.signup(userDTO);

    return "redirect:/auth/login";
  }

  @GetMapping("/admin/login")
  public String login() {
    return "user/login";
  }

  @PostMapping("/admin/logout")
  public String logout(HttpServletRequest request, HttpServletResponse response) {
    // 세션 무효화
    request.getSession().invalidate();

    // 로그아웃 후 리다이렉트
    return "redirect:/admin/login?logout";
  }
//  @GetMapping("/admin/dashboard")
//  public String dashboard(){
//    return "/common/dashboard";
//  }
}

