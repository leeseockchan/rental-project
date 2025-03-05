package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.UserDTO;
import com.road_friends.rentalcar.mapper.UserAdminMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserAdminService {

    private final UserAdminMapper userAdminMapper;

    // 사용자 검색
    public List<UserDTO> searchUsersByName(String name) {
        return userAdminMapper.findUsersByName(name);
    }

    // 전체 회원 수 조회
    public int getUserCount() {
        return userAdminMapper.getUserCount();
    }

    // 활성 사용자 수 조회
    public int getActiveUserCount() {
        return userAdminMapper.getActiveUserCount();
    }

    // 탈퇴 사용자 수 조회
    public int getInactiveUserCount() {
        return userAdminMapper.getInactiveUserCount();
    }

    // 남자 사용자 수 조회
    public int getMaleUserCount() {
        return userAdminMapper.getMaleUserCount();
    }

    // 여자 사용자 수 조회
    public int getFemaleUserCount() {
        return userAdminMapper.getFemaleUserCount();
    }

    // 현재 연도 계산
    private int getCurrentYear() {
        return LocalDate.now().getYear();
    }

    // 연령대별 사용자 수 조회
    public int getAgeGroupCount(int minAge, int maxAge) {
        int currentYear = getCurrentYear();
        int startYear = currentYear - maxAge; // 연령대 시작 연도
        int endYear = currentYear - minAge; // 연령대 끝 연도
        return userAdminMapper.getAgeGroupCount(startYear, endYear);
    }

    // 20대 사용자 수 조회
    public int getTwentiesCount() {
        return getAgeGroupCount(20, 29);
    }

    // 30대 사용자 수 조회
    public int getThirtiesCount() {
        return getAgeGroupCount(30, 39);
    }

    // 40대 사용자 수 조회
    public int getFortiesCount() {
        return getAgeGroupCount(40, 49);
    }

    // 50대 사용자 수 조회
    public int getFiftiesCount() {
        return getAgeGroupCount(50, 59);
    }

    // 60대 이상 사용자 수 조회
    public int getSixtiesCount() {
        return getAgeGroupCount(60, 100);
    }

    public UserAdminService(UserAdminMapper userAdminMapper) {
        this.userAdminMapper = userAdminMapper;
    }


    // 모든 사용자 조회 (관리자용)
    public List<UserDTO> getAllUsers() {
        return userAdminMapper.getAllUsers();
    }

    // 특정 사용자 상세 조회 (관리자용)
    public UserDTO getUserDetail(Long userNum) {
        return userAdminMapper.getUserDetail(userNum);
    }

    // 특정 사용자 정보 수정 (관리자용)
    public boolean updateUser(UserDTO userDto) {
        return userAdminMapper.updateUser(userDto) > 0;
    }
}