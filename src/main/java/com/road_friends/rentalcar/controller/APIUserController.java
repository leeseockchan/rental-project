package com.road_friends.rentalcar.controller;


import com.road_friends.rentalcar.dto.APIUserDto;
import com.road_friends.rentalcar.service.APIUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class APIUserController {

    private final APIUserService apiUserService;

    public APIUserController(APIUserService apiUserService) {
        this.apiUserService = apiUserService;
    }

    // 로그인한 사용자의 정보 조회
    @GetMapping("/{userId}")
    public APIUserDto getUserInfo(@PathVariable("userId") String userId) {
        return apiUserService.getUserInfo(userId);
    }

    // 로그인한 사용자의 정보 수정
    @PutMapping("/{userId}")
    public String updateUserInfo(@PathVariable("userId") String userId, @RequestBody APIUserDto apiUserDto) {
        apiUserDto.setUserId(userId);
        apiUserService.updateUserInfo(apiUserDto);
        return "회원정보가 수정되었습니다.";
    }

    // 로그인한 사용자의 탈퇴 (enabled 값을 false 변경)
    @DeleteMapping("/{userId}")
    public String disableUser(@PathVariable("userId") String userId) {
        apiUserService.disableUser(userId);
        return "회원 탈퇴처리가 완료되었습니다.";
    }



}
