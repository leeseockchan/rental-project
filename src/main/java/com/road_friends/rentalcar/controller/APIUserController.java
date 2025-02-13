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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("/api/auth")
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
              new UsernamePasswordAuthenticationToken(user.get("user_id"), user.get("user_password"))
      );

      // 인증된 사용자 정보 가져오기
      UserDetails userDetails = customUserDetailService.loadUserByUsername(user.get("user_id"));

      // 사용자의 권한(role) 가져오기
      List<String> roles = userDetails.getAuthorities()
              .stream()
              .map(GrantedAuthority::getAuthority)
              .collect(Collectors.toList());

      // JWT 토큰 생성
      String token = jwtUtil.generateToken(userDetails.getUsername(), roles);

      return Map.of("token", token);

    } catch (AuthenticationException e) {
      throw new RuntimeException("Invalid credentials");
    }
  }

}
