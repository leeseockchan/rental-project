package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.CarDto;
import com.road_friends.rentalcar.dto.ModelDto;
import com.road_friends.rentalcar.dto.ParkingDto;
import com.road_friends.rentalcar.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/api/admin/vehicles")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/districts")
    @ResponseBody
    public List<String> getDistricts(@RequestParam String province) {
        return carService.getDistrictsByProvince(province);
    }

    // 지역별 차량 검색
    @GetMapping("/search")
    @ResponseBody
    public List<CarDto> searchByDistrict(@RequestParam String district) {
        return carService.findByDistrict(district);
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
        CarDto car = carService.findByCarId(carId);
        model.addAttribute("car", car);
        return "car_page/detail";
    }

    //    차량 관리 추가
    @GetMapping("/add")
    public String addCarStatus(Model model) {
        model.addAttribute("mBrandList", carService.carBrandList());
        model.addAttribute("mNameList", carService.modelNameList());
        model.addAttribute("yearList", carService.carYearList());
        model.addAttribute("fuelList", carService.carFuelList());
        model.addAttribute("gradeList", carService.carGradeList());
        model.addAttribute("provinceList", carService.parkingProvinceList());
        model.addAttribute("districtList", new ArrayList<String>());

        CarDto newCar = new CarDto();
        newCar.setModel(new ModelDto());
        newCar.setParking(new ParkingDto());
        model.addAttribute("newCar", newCar);

        return "car_page/add";
    }
    @PostMapping("/add")
    public String addCarStatus(@ModelAttribute CarDto carDto) {
        carService.insertCar(carDto);
        return "redirect:/api/admin/vehicles";
    }

    //    차량 관리 수정
    @GetMapping("/modify/{carId}")
    public String modifyCarStatus(@PathVariable int carId, Model model) {
        CarDto modifyCar = carService.findByCarId(carId);
        model.addAttribute("mBrandList", carService.carBrandList());
        model.addAttribute("mNameList", carService.modelNameList());
        model.addAttribute("yearList", carService.carYearList());
        model.addAttribute("fuelList", carService.carFuelList());
        model.addAttribute("gradeList", carService.carGradeList());
        model.addAttribute("provinceList", carService.parkingProvinceList());
        List<String> districtList =  carService.getDistrictsByProvince(modifyCar.getParking().getParkingProvince());
        model.addAttribute("districtList", districtList);
        model.addAttribute("modify", modifyCar);
        return "car_page/modify";
    }

    @PutMapping("/modify/{carId}")
    @ResponseBody
    public  ResponseEntity<Map<String, Object>> modifyCarStatusPost(@PathVariable int carId,
                                      @RequestBody CarDto carDto) {
        carDto.setCarId(carId);
        carService.modifyCarStatus(carDto);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "수정 완료");
        return  ResponseEntity.ok(response);
    }
    // 행정 지역 가져오기 엔드포인트
    @GetMapping("/api/districts/{province}")
    @ResponseBody
    public List<String> getDistrictsByProvince(@PathVariable String province) {
        return carService.getDistrictsByProvince(province); // 도/시에 해당하는 행정구역 반환
    }
   
    //    차량 상태 관리 삭제
    @DeleteMapping("/{carId}")
    public String deleteCarStatus(@PathVariable int carId) {
        carService.deleteCarStatus(carId);
        return "redirect:/api/admin/vehicles";
    }
}

