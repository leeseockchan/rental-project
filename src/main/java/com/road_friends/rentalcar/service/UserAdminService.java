package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.PageDto;
import com.road_friends.rentalcar.dto.UserDTO;
import com.road_friends.rentalcar.mapper.UserAdminMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserAdminService {

    private final UserAdminMapper userAdminMapper;

    public UserAdminService(UserAdminMapper userAdminMapper) {
        this.userAdminMapper = userAdminMapper;
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
        int startYear = currentYear - maxAge;
        int endYear = currentYear - minAge;
        return userAdminMapper.getAgeGroupCount(startYear, endYear);
    }

    public int getTwentiesCount() { return getAgeGroupCount(20, 29); }
    public int getThirtiesCount() { return getAgeGroupCount(30, 39); }
    public int getFortiesCount() { return getAgeGroupCount(40, 49); }
    public int getFiftiesCount() { return getAgeGroupCount(50, 59); }
    public int getSixtiesCount() { return getAgeGroupCount(60, 100); }

    // 전체 사용자 목록 조회 (페이지네이션 적용)
    public List<UserDTO> getAllUsers(int page, int size) {
        int offset = (page - 1) * size;
        return userAdminMapper.selectAllUsers(offset, size);
    }

    // 아이디로 사용자 목록 검색 (페이지네이션 적용)
    public List<UserDTO> searchUsersById(String userId, int page, int size) {
        int offset = (page - 1) * size;
        return userAdminMapper.searchUsersById(userId, offset, size);
    }

    // 전체 사용자 목록 조회 (아이디 검색 포함)
    public int getUserCountById(String userId) {
        if (userId == null || userId.isEmpty()) {
            return userAdminMapper.selectUserCount();
        } else {
            return userAdminMapper.searchUserCountById(userId);
        }
    }

    // 특정 사용자 상세 조회
    public UserDTO getUserDetail(Long userNum) {
        return userAdminMapper.getUserDetail(userNum);
    }

    // 특정 사용자 정보 수정
    public boolean updateUser(UserDTO userDto) {
        return userAdminMapper.updateUser(userDto) > 0;
    }
}
