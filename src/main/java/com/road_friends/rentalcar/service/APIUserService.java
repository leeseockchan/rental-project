package com.road_friends.rentalcar.service;


import com.road_friends.rentalcar.dto.APIUserDto;
import com.road_friends.rentalcar.mapper.APIUserMapper;
import org.springframework.stereotype.Service;

@Service
public class APIUserService {

    private final APIUserMapper userMapper;

    public APIUserService(APIUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    // 로그인한 사용자의 정보 조회
    public APIUserDto getUserInfo(int userId) {
        return userMapper.findById(userId);
    }

    // 로그인한 사용자의 정보 수정
    public void updateUserInfo(int userId, APIUserDto userDto) {
        userDto.setUserNum(userId);  // 수정할 사용자의 ID 설정
        userMapper.update(userDto);
    }

    // 로그인한 사용자의 탈퇴
    public void deleteUser(int userId) {
        userMapper.delete(userId);
    }


}
