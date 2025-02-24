package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.CarDto;
import com.road_friends.rentalcar.dto.FastReservationDto;
import com.road_friends.rentalcar.dto.ModelDto;
import com.road_friends.rentalcar.dto.ParkingDto;
import com.road_friends.rentalcar.mapper.FastReservationMapper;
import com.road_friends.rentalcar.service.FastReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/quick-rent")
public class FastReservationController {

    private final FastReservationService fastReservationService;
    private final FastReservationMapper fastReservationMapper;

    public FastReservationController(FastReservationService fastReservationService, FastReservationMapper fastReservationMapper) {
        this.fastReservationService = fastReservationService;
        this.fastReservationMapper = fastReservationMapper;
    }


    // 입력받은 지역, 시간으로 이용 가능한 차량 조회
    @PostMapping("/cars")
    public ResponseEntity <Map<String, Object>> selectCars(@RequestBody Map<String, Object> requestBody) {

        // 차량 조회
        String province = (String) requestBody.get("province");
        String district = (String) requestBody.get("district");
        LocalDateTime rentalDatetime = LocalDateTime.parse((String) requestBody.get("rental_datetime"));
        LocalDateTime returnDatetime = LocalDateTime.parse((String) requestBody.get("return_datetime"));


        // 특정 조건으로 차량 검색 (필터링)
        String modelCategory = (String) requestBody.get("model_category");
        String modelName = (String) requestBody.get("model_name");
//        int modelAmountHour = (int) requestBody.get("modelAmountHour");
//        int modelAmountDay = (int) requestBody.get("modelAmountDay");

        Map<String, Object> availableCars = fastReservationService.getAvailableCars(province, district, rentalDatetime, returnDatetime, modelCategory,modelName);

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

    // 예약
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
        ModelDto model = fastReservationService.getModelById(carId);
        carDetail.setModel(model);

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
