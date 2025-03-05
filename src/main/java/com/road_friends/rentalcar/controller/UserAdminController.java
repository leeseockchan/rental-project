package com.road_friends.rentalcar.controller;


import com.road_friends.rentalcar.dto.UserDTO;
import com.road_friends.rentalcar.service.UserAdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class UserAdminController {

    private final UserAdminService userAdminService;

    public UserAdminController(UserAdminService userAdminService) {
        this.userAdminService = userAdminService;
    }

    // admin 사용자 관리
    @GetMapping
    public String getAllUsers(Model model) {
        // 회원 목록 조회
        List<UserDTO> users = userAdminService.getAllUsers();
        model.addAttribute("users", users);

        //회원 수 조회
        int userCount = userAdminService.getUserCount(); // 회원 수 조회
        model.addAttribute("userCount", userCount); // Thymeleaf로 전달

        //활성사용자 조회
        int activeUserCount = userAdminService.getActiveUserCount(); // 활성 사용자 수
        model.addAttribute("activeUserCount", activeUserCount);

        //탈퇴사용자
        int inactiveUserCount = userAdminService.getInactiveUserCount(); // 탈퇴 사용자 수
        model.addAttribute("inactiveUserCount", inactiveUserCount);

        //성별 통계
        int maleUserCount = userAdminService.getMaleUserCount(); // 남자 회원 수
        int femaleUserCount = userAdminService.getFemaleUserCount(); // 여자 회원 수
        model.addAttribute("maleUserCount", maleUserCount);
        model.addAttribute("femaleUserCount", femaleUserCount);

        //연령통계
        int twentiesCount = userAdminService.getTwentiesCount(); // 20대
        int thirtiesCount = userAdminService.getThirtiesCount(); // 30대
        int fortiesCount = userAdminService.getFortiesCount(); // 40대
        int fiftiesCount = userAdminService.getFiftiesCount(); // 50대
        int sixtiesCount = userAdminService.getSixtiesCount(); // 60대 이상

        model.addAttribute("users", users);
        model.addAttribute("twentiesCount", twentiesCount);
        model.addAttribute("thirtiesCount", thirtiesCount);
        model.addAttribute("fortiesCount", fortiesCount);
        model.addAttribute("fiftiesCount", fiftiesCount);
        model.addAttribute("sixtiesCount", sixtiesCount);

        return "users/users-list"; // templates/user.html로 이동
    }

    // 특정 사용자 상세 조회
    @GetMapping("/{userNum}")
    public String getUserDetail(@PathVariable("userNum") Long userNum, Model model) {
        UserDTO user = userAdminService.getUserDetail(userNum);
        model.addAttribute("user", user);
        return "users/users-detail"; // templates/user-detail.html로 이동
    }

    // 특정 사용자 정보 수정
    @PostMapping("/{userNum}")
    public String updateUser(@PathVariable("userNum") Long userNum, @ModelAttribute UserDTO userDto) {
        userDto.setUserNum(userNum);
        boolean updated = userAdminService.updateUser(userDto);

        // 수정 성공 시 목록으로 리다이렉트
        return updated ? "redirect:/admin/users" : "error";
    }

}
