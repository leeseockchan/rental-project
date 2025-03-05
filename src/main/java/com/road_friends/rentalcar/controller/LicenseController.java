package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.LicenseDto;
import com.road_friends.rentalcar.service.LicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/license")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class LicenseController {

  private final LicenseService licenseService;

  // 면허증 정보 등록
  @PostMapping("/register")
  public ResponseEntity<String> registerLicense(
          @RequestParam("licenseNum") String licenseNum,
          @RequestParam("licenseDate") String licenseDate,
          @RequestParam("licenseEndDate") String licenseEndDate,
          @RequestParam("userNum") Long userNum,
          @RequestParam("licensePhoto") MultipartFile licensePhoto
  ) {

    String photoPath = saveLicensePhoto(licensePhoto);

    LicenseDto licenseDto = new LicenseDto();
    licenseDto.setLicenseNum(licenseNum);
    licenseDto.setLicenseDate(LocalDate.parse(licenseDate));
    licenseDto.setLicenseEndDate(LocalDate.parse(licenseEndDate));
    licenseDto.setUserNum(userNum);
    licenseDto.setLicensePhotoPath(photoPath);

    licenseService.registerLicense(licenseDto);
    return ResponseEntity.ok("면허증 정보와 사진이 등록되었습니다.");
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