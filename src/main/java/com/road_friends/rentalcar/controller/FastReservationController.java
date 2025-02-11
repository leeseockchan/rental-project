package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.CarDto;
import com.road_friends.rentalcar.dto.FastReservationDto;
import com.road_friends.rentalcar.service.CarService;
import com.road_friends.rentalcar.service.FastReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class FastReservationController {

    private final CarService carService;
    private final FastReservationService fastReservationService;

    // 차량 목록 조회
    @GetMapping
    public ResponseEntity<List<CarDto>> getAllCars() {
        List<CarDto> cars = carService.getAllCars();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    // 특정 차량 조회
    @GetMapping("/{carId}")
    public ResponseEntity<CarDto> getCarById(@PathVariable("carId") int carId) {
        CarDto car = carService.getCarById(carId);
        return car != null ? new ResponseEntity<>(car, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/reservation")
    public String reservation(){
        return "reservation";
    }

    // 차량 예약
    @PostMapping("/reservation")
    public void reserveation(Model model, FastReservationDto fastReservationDto){
        fastReservationService.fastReserve(fastReservationDto);
        model.addAttribute("dto",fastReservationDto);
//        System.out.println(fastReservationDto.toString());
    }



}
