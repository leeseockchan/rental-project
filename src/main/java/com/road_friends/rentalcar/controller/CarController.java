package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.CarDto;
import com.road_friends.rentalcar.service.CarService;
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

//    차량 관리 목록 조회(수원시 차량들)
    @GetMapping
    public String showCarStatus(Model model){
        List<CarDto> cars = carService.showCarStatusList();
        model.addAttribute("cars", cars);
        return "car_page/list";
    }

//    차량 관리 상세보기
    @GetMapping("/{carId}")
    public String detailCarStatus(@PathVariable int carId, Model model){
        CarDto detailCar = carService.findByCarId(carId);
        return "car_page/detail";
    }

//    차량 관리 추가
    @GetMapping("/add")
    public String addCarStatus(Model model){
        model.addAttribute("addCar", new CarDto());
        return "car_page/add";
    }
    @PostMapping
    public String addCarStatus(@ModelAttribute CarDto carDto){
        carService.insertCarStatus(carDto);
        return "redirect:/api/admin/vehicles";
    }

//    차량 관리 수정
    @GetMapping("/modify/{carId}")
    public String modifyCarStatus(@PathVariable int carId, Model model){
        CarDto modifyCar = carService.findByCarId(carId);
        model.addAttribute("modify", modifyCar);
        return "car_page/modify";
    }

    @PostMapping("/modify/{carId}")
    public String modifyCarStatusPost(@PathVariable int carId,
                                      @ModelAttribute CarDto carDto){
        carDto.setCarId(carId);
        carService.modifyCarStatus(carDto);
        return "redirect:/api/admin/vehicles";
    }

//    차량 상태 관리 삭제
    @DeleteMapping("/{carId}")
    public String deleteCarStatus(@PathVariable int carId){
        carService.deleteCarStatus(carId);
        return "redirect:/api/admin/vehicles";
    }

}

