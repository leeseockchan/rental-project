package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.AdminCarDto;
import com.road_friends.rentalcar.dto.AdminParkingDto;
import com.road_friends.rentalcar.service.AdminParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/parkings")
public class AdminParkingController {

    @Autowired
    AdminParkingService adminParkingService;

    @GetMapping("/districts")
    @ResponseBody
    public List<String> getDistricts(@RequestParam String province) {
        return adminParkingService.getDistrictsByProvince(province);
    }

    @GetMapping("/search")
    @ResponseBody
    public List<AdminParkingDto> searchByProvinceAndDistrict(@RequestParam String province, @RequestParam String district) {
        return adminParkingService.findByProvinceAndDistrict(province, district);
    }

    private List<String> getProvinceList() {
        return List.of("서울특별시", "인천광역시" , "대전광역시" ,"부산광역시" , "대구광역시" ,"울산광역시" ,"광주광역시" ,"세종특별자치시" ,
                "경기도", "충청남도", "충청북도", "경상북도", "경상남도", "강원도", "전라북도", "전라남도", "제주도");
    }

    @GetMapping
    public String showAllParking(Model model) {
        model.addAttribute("provinceList", getProvinceList());

        Map<String, Integer> parkingStats = adminParkingService.getParkingStatistics();
        model.addAttribute("parkingStats", parkingStats);

        List<AdminParkingDto> top5ParkingStats = adminParkingService.getTop5ParkingStats();
        model.addAttribute("top5ParkingStats", top5ParkingStats);

        List<Map<String, Object>> parkingCountByRegion = adminParkingService.getParkingCountByRegion();
        model.addAttribute("parkingCountByRegion", parkingCountByRegion);

        return "parking/parking-list";
    }

    //    주차장 상세 보기
    @GetMapping("/{parkingId}")
    public String detailByParking(@PathVariable int parkingId, Model model) {
        // 주차장 상세 정보 가져오기
        AdminParkingDto parkingDetail = adminParkingService.findByParking(parkingId);
        // 해당 주차장이 보유한 차량 목록 가져오기
        List<AdminCarDto> carList = adminParkingService.findCarsByParking(parkingId);
        model.addAttribute("parkingDetail", parkingDetail);
        model.addAttribute("carList", carList);
        return "parking/parking-detail";
    }

    // 주차장 추가하기
    @GetMapping("/add")
    public String addParking(Model model) {
        model.addAttribute("provinceList", getProvinceList());
        model.addAttribute("adminParkingDto", new AdminParkingDto());
        model.addAttribute("provinceList", adminParkingService.getAllProvinces());
        model.addAttribute("districtList", new ArrayList<String>());
        return "parking/parking-create";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute AdminParkingDto adminParkingDto){
        adminParkingService.addParking(adminParkingDto);
        return "redirect:/admin/parkings";
    }

    //    주차장 정보 수정
    @GetMapping("/{parkingId}/modify")
    public String modify(@PathVariable int parkingId, Model model) {
        AdminParkingDto adminParkingDto = adminParkingService.findByParking(parkingId);
        model.addAttribute("provinceList", getProvinceList());
        model.addAttribute("modify", adminParkingDto);
        return "parking/parking-update";
    }
    @PutMapping("/{parkingId}/modify")
    public String modifyParking(@PathVariable int parkingId,
                                @ModelAttribute AdminParkingDto adminParkingDto) {
        adminParkingDto.setParkingId(parkingId);
        adminParkingService.modifyParking(adminParkingDto);
        return "redirect:/admin/parkings/" + parkingId;
    }

    //      주차장 삭제
    @DeleteMapping("/{parkingId}")
    public String deleteParking(@PathVariable int parkingId) {
        adminParkingService.deleteParking(parkingId);
        return "redirect:/admin/parkings";  // 삭제 후 목록 페이지로 리디렉션
    }
}