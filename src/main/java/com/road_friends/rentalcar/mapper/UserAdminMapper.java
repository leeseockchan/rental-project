package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserAdminMapper {

    // 사용자 검색
    List<UserDTO> findUsersByName(String name);

    // 전체 회원 수 조회
    int getUserCount();

    // 활성 사용자 수 조회
    int getActiveUserCount();

    // 탈퇴 사용자 수 조회
    int getInactiveUserCount();

    // 남자 사용자 수 조회 (user_gender = 0)
    int getMaleUserCount();

    // 여자 사용자 수 조회 (user_gender = 1)
    int getFemaleUserCount();

    // 연령대별 사용자 수 조회
    int getAgeGroupCount(@Param("startYear") int startYear, @Param("endYear") int endYear);

    // 모든 사용자 조회 (관리자용)
    List<UserDTO> getAllUsers();

    // 특정 사용자 상세 조회 (관리자용)
    UserDTO getUserDetail(Long userNum);

    // 특정 사용자 정보 수정 (관리자용)
    int updateUser(UserDTO userDto);
}
