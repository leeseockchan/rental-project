package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.LicenseDto;
import com.road_friends.rentalcar.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface APIUserMapper {
  void save(UserDto userDTO);
  void insertUserRole(@Param("userId") Long userId, @Param("roleId") int roleId);
  // 면허증 정보 삽입
  void saveLicense(LicenseDto licenseDto);

  // 사용자 권한을 ROLE_VERIFIED (3)로 변경
  void updateUserRole(@Param("userNum") Long userNum, @Param("roleId") int roleId);

  // userNum을 이용해 userId 조회
  String getUserIdByUserNum(@Param("userNum") Long userNum);

  // 사용자의 새로운 권한 리스트 조회
  List<String> getUserRoles(@Param("userNum") Long userNum);

  // 회원 조회
  UserDto findByUserId(@Param("userId") String userId);

  // 회원 탈퇴
  int deleteUser(@Param("userNum") Long userNum);
}
