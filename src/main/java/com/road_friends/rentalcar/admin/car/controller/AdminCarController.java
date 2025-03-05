package com.road_friends.rentalcar.admin.car.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.road_friends.rentalcar.admin.car.dto.AdminCarDto;
import com.road_friends.rentalcar.admin.car.dto.AdminModelDto;
import com.road_friends.rentalcar.admin.car.dto.AdminParkingDto;
import com.road_friends.rentalcar.admin.car.service.AdminCarService;
import com.road_friends.rentalcar.admin.car.service.AdminParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("api/admin/vehicles")
public class AdminCarController {

    @Autowired
    private AdminCarService adminCarService;

    @Autowired
    private AdminParkingService adminParkingService;

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

    // 1. 도/시(province) 목록 조회
//    @GetMapping("/provinces")
//    public ResponseEntity<List<String>> getProvinces() {
//        List<String> provinces = adminParkingService.getAllProvinces();
//        return ResponseEntity.ok(provinces);
//    }

    // 3. 선택된 행정구역의 주차장 목록 조회
    @GetMapping("/parkings")
    public ResponseEntity<List<AdminParkingDto>> getParkings(
            @RequestParam("province") String province,
            @RequestParam("district") String district) {
        List<AdminParkingDto> parkings = adminParkingService.getParkingsByDistrict(province, district);
        return ResponseEntity.ok(parkings);
    }

    // 주차 도/시 리스트
    private List<String> getProvinceList() {
        return List.of("서울특별시", "경기도", "충청북도", "충청남도",
                "경상북도", "경상남도", "전라북도", "전라남도", "제주도");
    }

    private final ObjectMapper objectMapper = new ObjectMapper(); // JSON 변환기

    //    차량 관리 목록 조회
    @GetMapping
    public String showCarStatus(Model model) {
        List<Map<String, Object>> carGrades = adminCarService.getCarGradeCount();
        List<Map<String, Object>> carRanking = adminCarService.getCarRanking();
        List<Map<String, Object>> carBrands = adminCarService.getCarBrandCount();

        model.addAttribute("carGrades", carGrades);
        model.addAttribute("carRanking", carRanking);
        model.addAttribute("carBrands", carBrands);

        // 🚗 차량 통계 데이터 가져오기
        Map<String, Integer> vehicleStats = adminCarService.getVehicleStatistics();

        // 🔹 통계 데이터 모델에 추가
        model.addAttribute("totalVehicles", vehicleStats.get("total"));
        model.addAttribute("rentedVehicles", vehicleStats.get("rented"));
        model.addAttribute("repairVehicles", vehicleStats.get("repair"));

        model.addAttribute("provinceList", adminCarService.parkingProvinceList());
        return "car/car-list";
    }

    //    차량 관리 상세보기
    @GetMapping("/{carId}")
    public String detailCarStatus(@PathVariable int carId, Model model) {
        AdminCarDto car = adminCarService.findByCarId(carId);
        model.addAttribute("car", car);
        return "car/car-detail";
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
        return "car/car-create";
    }
    @PostMapping("/add")
    public String addCarStatus(@ModelAttribute AdminCarDto adminCarDto) {
        adminCarService.insertCar(adminCarDto);
        return "redirect:/api/admin/vehicles";
    }

    //    차량 관리 수정
    @GetMapping("/modify/{carId}")
    public String modifyCarStatus(@PathVariable int carId, Model model) {
        // 차량 정보 가져오기
        AdminCarDto modifyCar = adminCarService.findByCarId(carId);

        // 제조사, 모델명, 연식, 연료, 등급 리스트 추가
        model.addAttribute("mBrandList", adminCarService.carBrandList());
        model.addAttribute("mNameList", adminCarService.modelNameList());
        model.addAttribute("yearList", adminCarService.carYearList());
        model.addAttribute("fuelList", adminCarService.carFuelList());
        model.addAttribute("gradeList", adminCarService.carGradeList());

        // 도/시 (province) 리스트 추가
        model.addAttribute("provinceList", adminCarService.parkingProvinceList());

        // 차량이 등록된 주차장의 도/시 정보 가져오기
        String province = (modifyCar.getParking() != null) ? modifyCar.getParking().getParkingProvince() : null;
        String district = (modifyCar.getParking() != null) ? modifyCar.getParking().getParkingDistrict() : null;

        // 행정구역 리스트 조회
        List<String> districtList = (province != null) ? adminCarService.getDistrictsByProvince(province) : new ArrayList<>();
        model.addAttribute("districtList", districtList);

        // 주차장 리스트 조회
        List<AdminParkingDto> parkingList = (province != null && district != null) ?
                adminParkingService.getParkingsByDistrict(province, district) : new ArrayList<>();
        model.addAttribute("parkingList", parkingList);

        // 수정할 차량 정보 추가
        model.addAttribute("modify", modifyCar);

        return "car/car-update";
    }

    @PutMapping("/modify/{carId}")
    public ResponseEntity<Map<String, Object>> modifyCarStatusPost(
            @PathVariable int carId,
            @RequestBody AdminCarDto adminCarDto) {

        // 🔹 디버깅: 요청 데이터 확인
        System.out.println("🔹 수신된 데이터: " + adminCarDto);

        if (adminCarDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("success", false, "message", "잘못된 요청 데이터"));
        }

        // modelId를 직접 조회하여 설정
        int modelId = adminCarService.getModelIdByName(adminCarDto.getModel().getModelName());
        adminCarDto.setModelId(modelId);
        adminCarDto.setCarId(carId);

        adminCarService.modifyCarStatus(adminCarDto);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "수정 완료");
        return ResponseEntity.ok(response);
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

