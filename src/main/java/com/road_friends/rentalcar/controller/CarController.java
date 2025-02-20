package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.CarDto;
import com.road_friends.rentalcar.dto.ModelDto;
import com.road_friends.rentalcar.dto.ParkingDto;
import com.road_friends.rentalcar.service.CarService;
import com.road_friends.rentalcar.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/admin/vehicles")
public class CarController {

    @Autowired
    private CarService carService;
    @Autowired
    private ParkingService parkingService;


    //    차량 관리 목록 조회(경기도 차량들)
    @GetMapping
    public String showCarStatus(Model model) {
        List<CarDto> cars = carService.showCarStatusList();
        model.addAttribute("cars", cars);
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

        model.addAttribute("addCar", new CarDto());
        model.addAttribute("mBrandList", carService.carBrandList());
        model.addAttribute("mNameList", carService.modelNameList());
        model.addAttribute("yearList", carService.carYearList());
        model.addAttribute("fuelList", carService.carFuelList());
        model.addAttribute("gradeList", carService.carGradeList());
        model.addAttribute("provinceList", carService.parkingProvinceList());
        model.addAttribute("districtList", carService.parkingDistrictList());
        return "car_page/add";
    }
    @PostMapping("/add")
    public String addCarStatus(@ModelAttribute CarDto carDto,
                               @RequestParam("parkingProvince") String parkingProvince,
                               @RequestParam("parkingDistrict") String parkingDistrict,
                               @RequestParam("parkingName") String parkingName,
                               @RequestParam("modelBrand") String modelBrand,
                               @RequestParam("modelName") String modelName) {

        // 주차장 정보 ParkingDto 객체에 생성
        ParkingDto parkingDto = new ParkingDto();
        parkingDto.setParkingProvince(parkingProvince);
        parkingDto.setParkingDistrict(parkingDistrict);
        parkingDto.setParkingName(parkingName);

        // 모델 정보를 별도로 ModelDto 객체에 세팅
        ModelDto modelDto = new ModelDto();
        modelDto.setModelBrand(modelBrand);
        modelDto.setModelName(modelName);

        // 차량 정보를 처리
        carService.insertCarStatus(carDto, parkingDto, modelDto); // 서비스 메소드에서 처리
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
        model.addAttribute("modify", modifyCar);

        model.addAttribute("provinceList", carService.parkingProvinceList());
        model.addAttribute("districtList", carService.parkingDistrictList());
        return "car_page/modify";
    }

    @PutMapping("/modify/{carId}")
    public String modifyCarStatusPost(@PathVariable int carId,
                                      @ModelAttribute CarDto carDto) {
        carDto.setCarId(carId);
        carService.modifyCarStatus(carDto);
        return "redirect:/api/admin/vehicles";
    }

//    // AJAX로 행정구역 반환
//    @ResponseBody
//    @GetMapping("/regions")
//    public List<String> getRegions(@RequestParam("city") String city) {
//        return parkingService.findByParking();
//    }

    //    차량 상태 관리 삭제
    @DeleteMapping("/{carId}")
    public String deleteCarStatus(@PathVariable int carId) {
        carService.deleteCarStatus(carId);
        return "redirect:/api/admin/vehicles";
    }

}

