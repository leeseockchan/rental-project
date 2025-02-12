package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.component.JwtUtil;
import com.road_friends.rentalcar.dto.UserDTO;
import com.road_friends.rentalcar.mapper.APIUserMapper;
import com.road_friends.rentalcar.service.APIUserService;
import com.road_friends.rentalcar.service.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@RestController
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Controller
public class APIUserController {

  private final APIUserService apiUserService;
  private final JwtUtil jwtUtil;
  private final AuthenticationManager authenticationManager;
  private final CustomUserDetailService customUserDetailService;

  @PostMapping("/signup")
  public ResponseEntity<String> signup(@RequestBody UserDTO userDTO) {
    apiUserService.signup(userDTO);
    return ResponseEntity.ok("Signup successful");
  }

  @PostMapping("/login")
  public Map<String, String> login(@RequestBody Map<String, String> user) {
    try {
      Authentication authentication = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(user.get("username"), user.get("password"))
      );

      String token = jwtUtil.generateToken(authentication.getName());
      return Map.of("token", token);

    } catch (AuthenticationException e) {
      throw new RuntimeException("Invalid credentials");
    }
  }


}
