package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.component.JwtUtil;
import com.road_friends.rentalcar.dto.LicenseDto;
import com.road_friends.rentalcar.dto.UserDTO;
import com.road_friends.rentalcar.mapper.APIUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class APIUserService {

  private final APIUserMapper apiUserMapper;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtil jwtUtil;

  //일반회원가입
  public void signup(UserDTO userDTO) {
    String encodedPw = passwordEncoder.encode(userDTO.getUserPassword());
    userDTO.setUserPassword(encodedPw);
    userDTO.setEnabled(userDTO.isEnabled());

    // 사용자 등록
    apiUserMapper.save(userDTO);
    // 권한 등록
    apiUserMapper.insertUserRole(userDTO.getUserNum(), 1);
  }

  //면허증 정보 업데이트
  public String updateLicenseAndRole(Long userNum, LicenseDto licenseDto) {
    // 면허증 정보 저장
    apiUserMapper.saveLicense(licenseDto);

    // 권한을 ROLE_VERIFIED(3)로 변경
    apiUserMapper.updateUserRole(userNum, 3);

    // 새로운 권한 리스트 가져오기
    List<String> updatedRoles = apiUserMapper.getUserRoles(userNum);

    // userNum을 이용해 userId 가져오기
    String userId = apiUserMapper.getUserIdByUserNum(userNum);

    // 새로운 JWT 토큰 생성 (userNum 추가)
    return jwtUtil.generateToken(userId, userNum, updatedRoles);
  }
}
