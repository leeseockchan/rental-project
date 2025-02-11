package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.CarDto;
import com.road_friends.rentalcar.dto.FastReservationDto;
import com.road_friends.rentalcar.service.CarService;
import com.road_friends.rentalcar.service.FastReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class FastReservationController {

    private final FastReservationService fastReservationService;
    private final CarService carService;

    // 차량 목록 조회
    @GetMapping("/vehicles")
    public String getAllCars(Model model){
        List<CarDto> cars = carService.getAllCars();
        model.addAttribute("cars",cars);
        return "car/list";
    }

    // 차량 상세 조회
    @GetMapping("/vehicles/{carId}")
    public String getCar(@PathVariable ("carId") int id,Model model){
        try{
            CarDto car = carService.getCarById(id);
            model.addAttribute("car",car);
        }catch(IllegalStateException e){
            model.addAttribute("message",e.getMessage());
            return "common/error";
        }
        return "car/detail";
    }


    // 차량 예약
    @PostMapping("/reservations")
    public void reserveation(Model model, FastReservationDto fastReservationDto){
        fastReservationService.fastReserve(fastReservationDto);
    }



}
