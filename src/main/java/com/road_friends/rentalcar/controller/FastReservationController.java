package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.CarDto;
import com.road_friends.rentalcar.dto.FastReservationDto;
import com.road_friends.rentalcar.service.FastReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quick-rent")
public class FastReservationController {

    private final FastReservationService fastReservationService;

    public FastReservationController(FastReservationService fastReservationService) {
        this.fastReservationService = fastReservationService;

    }

    // 모든 차량 목록 조회
    @GetMapping("/cars")
    public ResponseEntity<List<CarDto>> getAllCars() {
        List<CarDto> cars = fastReservationService.getAllCars();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    // 특정 차량 조회
    @GetMapping("/cars/{carId}")
    public ResponseEntity<CarDto> getCarById(@PathVariable("carId") int carId) {
        CarDto car = fastReservationService.getCarById(carId);
        return car != null ? new ResponseEntity<>(car, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // 예약 정보 조회
    @GetMapping("/reservations/{reservationId}")
    public ResponseEntity<FastReservationDto> getAllReservations(@PathVariable("reservationId") int reservationId){
        FastReservationDto reservation = fastReservationService.getReservationById(reservationId);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }



}
