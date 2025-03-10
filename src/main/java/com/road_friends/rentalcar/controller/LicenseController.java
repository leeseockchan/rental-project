package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.component.CustomUserDetails;
import com.road_friends.rentalcar.dto.LicenseDto;
import com.road_friends.rentalcar.service.LicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

  // 면허증 정보 조회 (JWT 토큰에서 사용자 정보 추출)
  @GetMapping("/myLicense")
  public ResponseEntity<LicenseDto> getLicenseInfo(@AuthenticationPrincipal CustomUserDetails userDetails) {
    Long userNum = userDetails.getUserNum();  // JWT 토큰에서 사용자 번호 가져오기

    LicenseDto licenseDto = licenseService.getLicenseInfo(userNum);  // 서비스에서 면허증 정보 조회

    if (licenseDto == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
              .body(null);  // 면허증 정보가 없으면 404 반환
    }

    return ResponseEntity.ok(licenseDto);  // 면허증 정보 반환
  }

  private String saveLicensePhoto(MultipartFile licensePhoto) {
    try {
      // 저장할 경로 설정
      String uploadDir = "C:/images/user/license/";
      Path directoryPath = Paths.get(uploadDir);

      // 디렉토리가 존재하지 않으면 생성
      if (!Files.exists(directoryPath)) {
        Files.createDirectories(directoryPath);
      }

      // 파일명 중복 방지를 위해 시간 기반 파일명 설정
      String fileName = System.currentTimeMillis() + "_" + licensePhoto.getOriginalFilename();
      Path filePath = directoryPath.resolve(fileName);

      Files.copy(licensePhoto.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
      return filePath.toString(); // 저장된 파일 경로 반환
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException("파일 저장 실패");
    }
  }

}