package com.road_friends.rentalcar.controller;


import com.road_friends.rentalcar.dto.APIUserDto;
import com.road_friends.rentalcar.service.APIUserAdminService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
public class APIUserAdminController {


    private final APIUserAdminService apiUserAdminService;

    public APIUserAdminController(APIUserAdminService apiAdminUserService) {
        this.apiUserAdminService = apiAdminUserService;
    }

    // 모든 사용자 조회 (관리자용)
    @GetMapping
    public List<APIUserDto> getAllUsers() {
        return apiUserAdminService.getAllUsers();
    }

    // 특정 사용자 상세 조회 (관리자용)
    @GetMapping("/{userNum}")
    public APIUserDto getUserDetail(@PathVariable("userNum") Long userNum){
        return apiUserAdminService.getUserDetail(userNum);
    }

    // 특정 사용자 정보 수정 (관리자용)
    @PutMapping("/{userNum}")
    public String updateUser(@PathVariable("userNum") Long userNum, @RequestBody APIUserDto apiUserDto){
        apiUserDto.setUserNum(userNum);
        boolean updated = apiUserAdminService.updateUser(apiUserDto);
        return updated ? "회원 정보가 수정되었습니다." : "회원 정보 수정 실패";

    }

}
