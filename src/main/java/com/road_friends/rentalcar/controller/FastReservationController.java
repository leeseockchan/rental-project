package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.CarDto;
import com.road_friends.rentalcar.dto.FastReservationDto;
import com.road_friends.rentalcar.service.FastReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/quick-rent")
public class FastReservationController {

    private final FastReservationService fastReservationService;

    public FastReservationController(FastReservationService fastReservationService) {
        this.fastReservationService = fastReservationService;

    }


    // 입력받은 지역, 시간으로 이용 가능한 차량 조회
    @PostMapping("/cars")
    public ResponseEntity<List<CarDto>> selectCars(@RequestBody Map<String, Object> requestBody) {
        String province = (String) requestBody.get("province");
        String district = (String) requestBody.get("district");
        LocalDateTime rentalDatetime = LocalDateTime.parse((String) requestBody.get("rental_datetime"));
        LocalDateTime returnDatetime = LocalDateTime.parse((String) requestBody.get("return_datetime"));

        List<CarDto> availableCars = fastReservationService.getAvailableCars(province, district, rentalDatetime, returnDatetime);

        return ResponseEntity.ok(availableCars);
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

    // 예약
    @PostMapping("/reservations")
    public ResponseEntity<FastReservationDto> reserve(@RequestBody FastReservationDto fastReservationDto) {

        // 가격 계산
        Long totalPrice = fastReservationService.getPrice(fastReservationDto);

        fastReservationService.reserve(fastReservationDto);
        System.out.println(totalPrice);

        return new ResponseEntity<>(fastReservationDto, HttpStatus.CREATED);
    }

    // 예약 취소
    @DeleteMapping("/reservations/{reservationId}")
    public ResponseEntity<Void> deleteCar(@PathVariable("reservationId") int reservationId) {
        fastReservationService.deleteReservation(reservationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 예약 수정
    @PutMapping("/reservations/{reservationId}")
    public ResponseEntity<FastReservationDto> updateReservation(@PathVariable("reservationId") int reservationId,
                                                                @RequestBody FastReservationDto fastReservationDto){
        fastReservationDto.setReservationId(reservationId);
        fastReservationService.updateReservation(fastReservationDto);
        return new ResponseEntity<>(fastReservationDto, HttpStatus.OK);
    }


}
