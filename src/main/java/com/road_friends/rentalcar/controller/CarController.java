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

import java.util.ArrayList;
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
        List<CarDto> cars = carService.findAllCar();
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
        model.addAttribute("districtList", carService.parkingDistrictList());
        model.addAttribute("modify", modifyCar);
        return "car_page/modify";
    }

    @PutMapping("/modify/{carId}")
    public String modifyCarStatusPost(@PathVariable int carId,
                                      @ModelAttribute CarDto carDto) {
        carDto.setCarId(carId);
        carService.modifyCarStatus(carDto);
        return "redirect:/api/admin/vehicles";
    }

    //    차량 상태 관리 삭제
    @DeleteMapping("/{carId}")
    public String deleteCarStatus(@PathVariable int carId) {
        carService.deleteCarStatus(carId);
        return "redirect:/api/admin/vehicles";
    }

}

