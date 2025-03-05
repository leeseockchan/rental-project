package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.component.CustomUserDetails;
import com.road_friends.rentalcar.dto.LicenseDto;
import com.road_friends.rentalcar.service.LicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/license")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class LicenseController {

  private final LicenseService licenseService;

  // 면허증 정보 등록
  @PostMapping("/register")
  public ResponseEntity<Map<String, String>> registerLicense(
          @RequestParam("licenseNum") String licenseNum,
          @RequestParam("licenseDate") String licenseDate,
          @RequestParam("licenseEndDate") String licenseEndDate,
          @RequestParam("licensePhoto") MultipartFile licensePhoto,
          @AuthenticationPrincipal CustomUserDetails userDetails  // 로그인된 사용자 정보 가져오기
  ) {
    Long userNum = userDetails.getUserNum();  // 로그인한 사용자의 ID

    // 사진 경로 설정 (E:/images/user/license)
    String photoPath = saveLicensePhoto(licensePhoto);

    LicenseDto licenseDto = new LicenseDto();
    licenseDto.setLicenseNum(licenseNum);
    licenseDto.setLicenseDate(LocalDate.parse(licenseDate));
    licenseDto.setLicenseEndDate(LocalDate.parse(licenseEndDate));
    licenseDto.setUserNum(userNum);  // 자동으로 로그인된 사용자 ID 사용
    licenseDto.setLicensePhotoPath(photoPath);

    licenseService.registerLicense(licenseDto);

    Map<String, String> response = new HashMap<>();
    response.put("message", "면허증 정보와 사진이 등록되었습니다.");
    return ResponseEntity.ok(response);  // JSON 응답 반환
  }


  private String saveLicensePhoto(MultipartFile licensePhoto) {
    try {
      String fileName = licensePhoto.getOriginalFilename();
      Path path = Paths.get("E:/images/user/license/" + fileName);
      Files.copy(licensePhoto.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
      return path.toString();
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException("파일 저장 실패");
    }
  }
}