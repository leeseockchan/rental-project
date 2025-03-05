package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.LicenseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LicenseMapper {
  // 면허증 정보 삽입
  void insertLicense(LicenseDto licenseDto);

  // 사용자 권한을 ROLE_VERIFIED (3)로 변경
  void updateUserRoleToVerified(@Param("userNum") Long userNum);
}
