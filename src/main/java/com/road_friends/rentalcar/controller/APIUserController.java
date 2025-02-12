package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.UserDTO;
import com.road_friends.rentalcar.service.APIUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Controller
public class APIUserController {

  private final APIUserService apiUserService;

  @PostMapping("/signup")
  public ResponseEntity<String> signup(@RequestBody UserDTO userDTO) {
    apiUserService.signup(userDTO);
    return ResponseEntity.ok("Signup successful");
  }
}
