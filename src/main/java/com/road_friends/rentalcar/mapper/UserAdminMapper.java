package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserAdminMapper {

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

    // 전체 사용자 목록 조회 (페이지네이션 적용) (관리자용)
    List<UserDTO> selectAllUsers(@Param("offset") int offset, @Param("size") int size);

    // 이름으로 사용자 목록 검색 (페이지네이션 적용) (관리자용)
    List<UserDTO> searchUsersByName(@Param("name") String name, @Param("offset") int offset, @Param("size") int size);

    // 전체 사용자 수 조회 (관리자용)
    int selectUserCount();

    // 이름으로 검색된 사용자 수 조회 (관리자용)
    int searchUserCountByName(@Param("name") String name);

    // 특정 사용자 상세 조회 (관리자용)
    UserDTO getUserDetail(Long userNum);

    // 특정 사용자 정보 수정 (관리자용)
    int updateUser(UserDTO userDto);

}
