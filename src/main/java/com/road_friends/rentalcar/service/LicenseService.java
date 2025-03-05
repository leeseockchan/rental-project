package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.LicenseDto;
import com.road_friends.rentalcar.mapper.LicenseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LicenseService {

  private final LicenseMapper licenseMapper;

  @Transactional
  public void registerLicense(LicenseDto licenseDto) {
    // 면허증 정보 저장
    licenseMapper.insertLicense(licenseDto);

    // 해당 유저의 권한을 ROLE_VERIFIED (3)로 변경
    licenseMapper.updateUserRoleToVerified(licenseDto.getUserNum());
  }

}
