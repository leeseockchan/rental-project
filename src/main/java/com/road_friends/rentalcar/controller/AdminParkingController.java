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
@RequestMapping("/api/admin/parkings")
public class AdminParkingController {

    @Autowired
    AdminParkingService adminParkingService;

    @GetMapping("/districts")
    @ResponseBody // JSON 응답을 반환하도록 설정
    public List<String> getDistricts(@RequestParam String province) {
        return adminParkingService.getDistrictsByProvince(province);
    }

    @GetMapping("/search")
    @ResponseBody
    public List<AdminParkingDto> searchByDistrict(@RequestParam String district) {
        return adminParkingService.findByDistrict(district);
    }

    // 주차 도/시 리스트
    private List<String> getProvinceList() {
        return List.of("서울특별시", "인천광역시" ,"경기도", "충청남도", "충청북도",
                "경상북도", "경상남도", "강원도", "전라북도", "전라남도", "제주도");
    }

    // 주차장 전체 목록
    @GetMapping
    public String showAllParking(Model model) {
        model.addAttribute("provinceList", getProvinceList()); // 시/도 리스트 추가

        // 주차장 통계 데이터 가져오기
        Map<String, Integer> parkingStats = adminParkingService.getParkingStatistics();
        model.addAttribute("parkingStats", parkingStats); // 템플릿에 데이터 추가

        // 기타 데이터들
        List<AdminParkingDto> top5ParkingStats = adminParkingService.getTop5ParkingStats();
        model.addAttribute("top5ParkingStats", top5ParkingStats);

        // 지역별 주차장 개수
        List<Map<String, Object>> parkingCountByRegion = adminParkingService.getParkingCountByRegion();
        model.addAttribute("parkingCountByRegion", parkingCountByRegion);

        return "parking/parking-list";
    }

    //    주차장 상세 보기
    @GetMapping("/{parkingId}")
    public String detailByParking(@PathVariable int parkingId, Model model) {
        AdminParkingDto parkingDetail = adminParkingService.findByParking(parkingId);
        model.addAttribute("parkingDetail", parkingDetail);
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
        return "redirect:/api/admin/parkings";
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
        return "redirect:/api/admin/parkings/" + parkingId;
    }

    //      주차장 삭제
    @DeleteMapping("/{parkingId}")
    public String deleteParking(@PathVariable int parkingId) {
        adminParkingService.deleteParking(parkingId);
        return "redirect:/api/admin/parkings";  // 삭제 후 목록 페이지로 리디렉션
    }
}