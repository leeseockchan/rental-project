package com.road_friends.rentalcar.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class LicenseDto {
  private Integer licenseId;      // 면허증 ID (PK)
  private String licenseNum;      // 면허증 번호 (고유값)
  private LocalDate licenseDate;  // 면허 발급일
  private LocalDate licenseEndDate; // 면허 만료일
  private Long userNum;           // 사용자 ID (FK)
}
