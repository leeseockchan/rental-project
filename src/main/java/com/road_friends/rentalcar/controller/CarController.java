package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.CarDto;
import com.road_friends.rentalcar.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    // 모든 차량 목록 조회
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

    // 차량 추가
    @PostMapping
    public ResponseEntity<CarDto> addCar(@RequestBody CarDto carDto) {
        carService.addCar(carDto);
        return new ResponseEntity<>(carDto, HttpStatus.CREATED);
    }

    // 차량 수정
    @PutMapping("/{carId}")
    public ResponseEntity<CarDto> updateCar(@PathVariable("carId") int carId, @RequestBody CarDto carDto) {
        carDto.setCarId(carId); // carId를 request body에 포함된 carDto의 carId로 설정
        carService.updateCar(carDto);
        return new ResponseEntity<>(carDto, HttpStatus.OK);
    }

    // 차량 삭제
    @DeleteMapping("/{carId}")
    public ResponseEntity<Void> deleteCar(@PathVariable("carId") int carId) {
        carService.deleteCar(carId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
