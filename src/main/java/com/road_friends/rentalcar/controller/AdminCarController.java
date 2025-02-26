package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.AdminCarDto;
import com.road_friends.rentalcar.dto.AdminModelDto;
import com.road_friends.rentalcar.dto.AdminParkingDto;
import com.road_friends.rentalcar.service.AdminCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/api/admin/vehicles")
public class AdminCarController {

    @Autowired
    private AdminCarService adminCarService;

    @GetMapping("/districts")
    @ResponseBody
    public List<String> getDistricts(@RequestParam String province) {
        return adminCarService.getDistrictsByProvince(province);
    }

    // 지역별 차량 검색
    @GetMapping("/search")
    @ResponseBody
    public List<AdminCarDto> searchByDistrict(@RequestParam String district) {
        return adminCarService.findByDistrict(district);
    }

    // 주차 도/시 리스트
    private List<String> getProvinceList() {
        return List.of("서울특별시", "경기도", "충청북도", "충청남도",
                "경상북도", "경상남도", "전라북도", "전라남도", "제주도");
    }

    //    차량 관리 목록 조회(경기도 차량들)
    @GetMapping
    public String showCarStatus(Model model) {
        model.addAttribute("provinceList", getProvinceList());
        return "car_page/list";
    }

    //    차량 관리 상세보기
    @GetMapping("/{carId}")
    public String detailCarStatus(@PathVariable int carId, Model model) {
        AdminCarDto car = adminCarService.findByCarId(carId);
        model.addAttribute("car", car);
        return "car_page/detail";
    }

    //    차량 관리 추가
    @GetMapping("/add")
    public String addCarStatus(Model model) {
        model.addAttribute("mBrandList", adminCarService.carBrandList());
        model.addAttribute("mNameList", adminCarService.modelNameList());
        model.addAttribute("yearList", adminCarService.carYearList());
        model.addAttribute("fuelList", adminCarService.carFuelList());
        model.addAttribute("gradeList", adminCarService.carGradeList());
        model.addAttribute("provinceList", adminCarService.parkingProvinceList());
        model.addAttribute("districtList", new ArrayList<String>());

        AdminCarDto newCar = new AdminCarDto();
        newCar.setModel(new AdminModelDto());
        newCar.setParking(new AdminParkingDto());
        model.addAttribute("newCar", newCar);

        return "car_page/add";
    }
    @PostMapping("/add")
    public String addCarStatus(@ModelAttribute AdminCarDto adminCarDto) {
        adminCarService.insertCar(adminCarDto);
        return "redirect:/api/admin/vehicles";
    }

    //    차량 관리 수정
    @GetMapping("/modify/{carId}")
    public String modifyCarStatus(@PathVariable int carId, Model model) {
        AdminCarDto modifyCar = adminCarService.findByCarId(carId);
        model.addAttribute("mBrandList", adminCarService.carBrandList());
        model.addAttribute("mNameList", adminCarService.modelNameList());
        model.addAttribute("yearList", adminCarService.carYearList());
        model.addAttribute("fuelList", adminCarService.carFuelList());
        model.addAttribute("gradeList", adminCarService.carGradeList());
        model.addAttribute("provinceList", adminCarService.parkingProvinceList());
        List<String> districtList =  adminCarService.getDistrictsByProvince(modifyCar.getParking().getParkingProvince());
        model.addAttribute("districtList", districtList);
        model.addAttribute("modify", modifyCar);
        return "car_page/modify";
    }

    @PutMapping("/modify/{carId}")
    @ResponseBody
    public  ResponseEntity<Map<String, Object>> modifyCarStatusPost(@PathVariable int carId,
                                      @RequestBody AdminCarDto adminCarDto) {
        adminCarDto.setCarId(carId);
        adminCarService.modifyCarStatus(adminCarDto);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "수정 완료");
        return  ResponseEntity.ok(response);
    }
    // 행정 지역 가져오기 엔드포인트
    @GetMapping("/api/districts/{province}")
    @ResponseBody
    public List<String> getDistrictsByProvince(@PathVariable String province) {
        return adminCarService.getDistrictsByProvince(province); // 도/시에 해당하는 행정구역 반환
    }
   
    //    차량 상태 관리 삭제
    @DeleteMapping("/{carId}")
    public String deleteCarStatus(@PathVariable int carId) {
        adminCarService.deleteCarStatus(carId);
        return "redirect:/api/admin/vehicles";
    }
}

