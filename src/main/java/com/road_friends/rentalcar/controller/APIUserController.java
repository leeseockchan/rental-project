package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.component.CustomUserDetails;
import com.road_friends.rentalcar.component.JwtUtil;
import com.road_friends.rentalcar.dto.LicenseDto;
import com.road_friends.rentalcar.dto.UserDto;
import com.road_friends.rentalcar.service.APIUserService;
import com.road_friends.rentalcar.service.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://127.0.0.1:5500")
@Controller
public class APIUserController {

  private final APIUserService apiUserService;
  private final JwtUtil jwtUtil;
  private final AuthenticationManager authenticationManager;
  private final CustomUserDetailService customUserDetailService;

  @PostMapping("/signup")
  public ResponseEntity<String> signup(@RequestBody UserDto userDTO) {
    apiUserService.signup(userDTO);
    return ResponseEntity.ok("Signup successful");
  }

  @PostMapping("/login")
  public Map<String, String> login(@RequestBody Map<String, String> user) {
    try {
      Authentication authentication = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(user.get("userId"), user.get("userPassword"))
      );

      // 인증된 사용자 정보 가져오기
      UserDetails userDetails = customUserDetailService.loadUserByUsername(user.get("userId"));

      // 사용자의 권한(role) 가져오기
      List<String> roles = userDetails.getAuthorities()
              .stream()
              .map(GrantedAuthority::getAuthority)
              .collect(Collectors.toList());

      // userNum 가져오기
      Long userNum = ((CustomUserDetails) userDetails).getUserNum();

      // JWT 토큰 생성 (userNum 포함)
      String token = jwtUtil.generateToken(userDetails.getUsername(), userNum, roles);

      return Map.of("token", token);

    } catch (AuthenticationException e) {
      throw new RuntimeException("Invalid credentials");
    }
  }


  @PostMapping("/register")
  public ResponseEntity<Map<String, String>> registerLicense(@RequestBody LicenseDto licenseDto) {
    // 면허 정보 저장 + 권한 변경 + 새로운 토큰 발급
    String newToken = apiUserService.updateLicenseAndRole(licenseDto.getUserNum(), licenseDto);

    return ResponseEntity.ok(Map.of("token", newToken));
  }

}
