package com.road_friends.rentalcar.controller;


import com.road_friends.rentalcar.dto.APIUserDto;
import com.road_friends.rentalcar.service.APIUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class APIUserController {

    private final APIUserService userService;

    public APIUserController(APIUserService userService) {
        this.userService = userService;
    }

    // 로그인한 사용자의 정보 조회
    @GetMapping("/{userId}")
    public APIUserDto getUserInfo(@PathVariable("userId") int userId) {
        return userService.getUserInfo(userId);
    }

    // 로그인한 사용자의 정보 수정
    @PutMapping("/{userId}")
    public String updateUserInfo(@PathVariable("userId") int userId, @RequestBody APIUserDto userDto) {
        userService.updateUserInfo(userId, userDto);
        return "회원정보가 수정되었습니다.";
    }

    // 로그인한 사용자의 탈퇴
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable("userId") int userId) {
        userService.deleteUser(userId);
        return "회원 탈퇴신청이 완료되었습니다. 이용해주셔서 감사합니다.";
    }



}
