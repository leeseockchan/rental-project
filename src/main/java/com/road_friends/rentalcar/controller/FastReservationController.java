package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.CarDto;
import com.road_friends.rentalcar.dto.FastReservationDto;
import com.road_friends.rentalcar.dto.ParkingDto;

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
    @GetMapping("/cars")
    public ResponseEntity <Map<String, Object>> selectCars(@RequestParam String province,
                                                           @RequestParam String district,
                                                           @RequestParam String rental_datetime,
                                                           @RequestParam String return_datetime,
                                                           @RequestParam(required = false) String model_category,
                                                           @RequestParam(required = false) String model_name,
                                                           @RequestParam(required = false) Integer end_price
                                                           ) {


        LocalDateTime rentalDatetime = LocalDateTime.parse(rental_datetime);
        LocalDateTime returnDatetime = LocalDateTime.parse(return_datetime);


        Map<String, Object> availableCars = fastReservationService.getAvailableCars(province, district, rentalDatetime, returnDatetime, model_category,model_name,end_price);

        return ResponseEntity.ok(availableCars);
    }


    // 특정 차량 상세 정보 조회
    @GetMapping("/cars/{carId}")
    public ResponseEntity <Map<String, Object>> getCarById(@PathVariable("carId") int carId,
                                             @RequestParam("rental_datetime") String rentalDatetimeStr,
                                             @RequestParam("return_datetime") String returnDatetimeStr) {

        // String을 LocalDateTime으로 변환
        LocalDateTime rentalDatetime = LocalDateTime.parse(rentalDatetimeStr);
        LocalDateTime returnDatetime = LocalDateTime.parse(returnDatetimeStr);

        // 차량 상세 정보 + 대여 가격 조회
        Map<String, Object> carDetail = fastReservationService.getCarInfo(carId,rentalDatetime,returnDatetime);


        return carDetail != null ? new ResponseEntity<>(carDetail, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // 예약 정보 조회
    @GetMapping("/reservations/{reservationId}")
    public ResponseEntity<FastReservationDto> getAllReservations(@PathVariable("reservationId") int reservationId){
        FastReservationDto reservation = fastReservationService.getReservationById(reservationId);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    // 예약 페이지
    @GetMapping("/reservations")
    public ResponseEntity<FastReservationDto> reserve(@RequestParam("car_id") int carId,
                                                    @RequestParam("rental_datetime") String rentalDatetimeStr,
                                                    @RequestParam("return_datetime") String returnDatetimeStr ){

        LocalDateTime rentalDatetime = LocalDateTime.parse(rentalDatetimeStr);
        LocalDateTime returnDatetime = LocalDateTime.parse(returnDatetimeStr);

        // 반납 가능 장소 조회
        List<ParkingDto> parkingList = fastReservationService.getParkingStation(rentalDatetime,returnDatetime,carId);

        // 차량 정보 조회
        CarDto carDetail = fastReservationService.getCarById(carId);
        carDetail.setModel(fastReservationService.getModelById(carId));

        FastReservationDto reservation = new FastReservationDto();

        reservation.setCarId(carId);
        reservation.setRentalDatetime(rentalDatetime);
        reservation.setReturnDatetime(returnDatetime);
        reservation.setRentalLocation(carDetail.getRentalStation());
        reservation.setParkingList(parkingList);
        reservation.setCarDto(carDetail);
        reservation.setTotalPrice(fastReservationService.getTotalPrice(carDetail,rentalDatetime,returnDatetime));

        return new ResponseEntity<> (reservation, HttpStatus.OK);
    }

    // 예약
    @PostMapping("/reservations")
    public ResponseEntity<FastReservationDto> reserve(@RequestBody FastReservationDto fastReservationDto) {

        fastReservationService.reserve(fastReservationDto);

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
