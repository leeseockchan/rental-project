package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.ParkingDto;
import com.road_friends.rentalcar.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/admin/parkings")
public class ParkingController {

    @Autowired
    ParkingService parkingService;

    @GetMapping("/districts")
    @ResponseBody // JSON 응답을 반환하도록 설정
    public List<String> getDistricts(@RequestParam String province) {
        return parkingService.getDistrictsByProvince(province);
    }

    @GetMapping("/search")
    @ResponseBody
    public List<ParkingDto> searchByDistrict(@RequestParam String district) {
        return parkingService.findByDistrict(district);
    }

    // 주차 도/시 리스트
    private List<String> getProvinceList() {
        return List.of("서울특별시", "경기도", "충청북도", "충청남도",
                "경상북도", "경상남도", "전라북도", "전라남도", "제주도");
    }

    // 주차장 전체 목록
    @GetMapping
    public String showAllParking(Model model) {
        List<ParkingDto> parkingList = parkingService.findAll();
        model.addAttribute("parkingList", parkingList);
        model.addAttribute("provinceList", getProvinceList()); // 시/도 리스트 추가
        return "parking_page/list";
    }

    // 주차장 추가하기
    @GetMapping("/add")
    public String addParking(Model model) {
        model.addAttribute("provinceList", getProvinceList());
        model.addAttribute("parkingDto", new ParkingDto());
        return "parking_page/add";
    }
}