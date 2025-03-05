package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.LicenseDto;
import com.road_friends.rentalcar.service.LicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/license")
@RequiredArgsConstructor
public class LicenseController {

  private final LicenseService licenseService;

  // 면허증 정보 등록
  @PostMapping("/register")
  public ResponseEntity<String> registerLicense(@RequestBody LicenseDto licenseDto) {
    licenseService.registerLicense(licenseDto);
    return ResponseEntity.ok("면허증 정보가 등록되었습니다.");
  }
}