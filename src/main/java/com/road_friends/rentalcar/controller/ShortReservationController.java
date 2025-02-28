package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.*;

import com.road_friends.rentalcar.service.ShortReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/short-rent")
public class ShortReservationController {

    private final ShortReservationService shortReservationService;

    public ShortReservationController(ShortReservationService shortReservationService) {
        this.shortReservationService = shortReservationService;
    }


    // 입력받은 지역, 시간으로 이용 가능한 차량 조회
    @GetMapping("/cars")
    public ResponseEntity <Map<String, Object>> selectCars(@RequestParam String province,
                                                           @RequestParam String district,
                                                           @RequestParam String reservation_s_start_date,
                                                           @RequestParam String reservation_s_end_date,
                                                           @RequestParam(required = false) String model_category,
                                                           @RequestParam(required = false) String model_name,
                                                           @RequestParam(required = false) Integer end_price
    ) {
        LocalDateTime rentalDatetime = LocalDateTime.parse(reservation_s_start_date);
        LocalDateTime returnDatetime = LocalDateTime.parse(reservation_s_end_date);
        Map<String, Object> availableCars = shortReservationService.getAvailableCars(province, district, rentalDatetime, returnDatetime, model_category, model_name, end_price);

        return ResponseEntity.ok(availableCars);
    }

    // 특정 차량 상세 정보 조회
    @GetMapping("/cars/{carId}")
    public ResponseEntity <Map<String, Object>> getCarById(@PathVariable("carId") int carId,
                                                           @RequestParam("reservation_s_start_date") String rentalDatetimeStr,
                                                           @RequestParam("reservation_s_end_date") String returnDatetimeStr) {
        // String을 LocalDateTime으로 변환
        LocalDateTime rentalDatetime = LocalDateTime.parse(rentalDatetimeStr);
        LocalDateTime returnDatetime = LocalDateTime.parse(returnDatetimeStr);

        // 차량 상세 정보 + 대여 가격 조회
        Map<String, Object> carDetail = shortReservationService.getCarInfo(carId,rentalDatetime,returnDatetime);


        return carDetail != null ? new ResponseEntity<>(carDetail, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // 예약 정보 조회
    @GetMapping("/reservations/{reservationId}")
    public ResponseEntity<ShortReservationDto> getAllReservations(@PathVariable("reservationId") int reservationId){
        ShortReservationDto reservation = shortReservationService.getReservationById(reservationId);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    // 예약 페이지
    @GetMapping("/reservations")
    public ResponseEntity<ShortReservationDto> reserve(@RequestParam("car_id") int carId,
                                                       @RequestParam("reservation_s_start_date") String rentalDatetimeStr,
                                                       @RequestParam("reservation_s_end_date") String returnDatetimeStr ){
        LocalDateTime rentalDatetime = LocalDateTime.parse(rentalDatetimeStr);
        LocalDateTime returnDatetime = LocalDateTime.parse(returnDatetimeStr);

        // 반납 가능 장소 조회(단기는 사용x)
        //List<ParkingDto> parkingList = shortReservationService.getParkingStation(rentalDatetime,returnDatetime,carId);

        // 차량 정보 조회
        CarDto carDetail = shortReservationService.getCarById(carId);
        carDetail.setModel(shortReservationService.getModelById(carId));

        ShortReservationDto reservation = new ShortReservationDto();

        reservation.setCarId(carId);
        reservation.setReservationSStartDate(rentalDatetime);
        reservation.setReservationSEndDate(returnDatetime);
        reservation.setRentalStationStart(carDetail.getRentalStation());
        //reservation.setParkingList(parkingList);
        reservation.setCarDto(carDetail);
        reservation.setTotalPrice(shortReservationService.getTotalPrice(carDetail,rentalDatetime,returnDatetime));

        return new ResponseEntity<> (reservation, HttpStatus.OK);
    }

    // 예약
    @PostMapping("/reservations")
    public ResponseEntity<ShortReservationDto> reserve(@RequestBody ShortReservationDto shortReservationDto) {

        shortReservationService.reserve(shortReservationDto);

        return new ResponseEntity<>(shortReservationDto, HttpStatus.CREATED);
    }

    // 예약 취소
    @DeleteMapping("/reservations/{reservationId}")
    public ResponseEntity<Void> deleteCar(@PathVariable("reservationSId") int reservationId) {
        shortReservationService.deleteReservation(reservationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 예약 수정
    @PutMapping("/reservations/{reservationId}")
    public ResponseEntity<ShortReservationDto> updateReservation(@PathVariable("reservationSId") int reservationId,
                                                                 @RequestBody ShortReservationDto shortReservationDto){
        shortReservationDto.setReservationSId(reservationId);
        shortReservationService.updateReservation(shortReservationDto);
        return new ResponseEntity<>(shortReservationDto, HttpStatus.OK);
    }


}