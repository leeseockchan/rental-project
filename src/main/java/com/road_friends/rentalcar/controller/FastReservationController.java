package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.CarDto;
import com.road_friends.rentalcar.dto.FastReservationDto;
import com.road_friends.rentalcar.dto.ParkingDto;
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
@CrossOrigin(origins = "http://localhost:3000")
public class FastReservationController {

    private final FastReservationService fastReservationService;

    public FastReservationController(FastReservationService fastReservationService) {
        this.fastReservationService = fastReservationService;

    }


    // 입력받은 지역, 시간으로 이용 가능한 차량 조회
    @PostMapping("/cars")
    public ResponseEntity<List<CarDto>> selectCars(@RequestBody Map<String, Object> requestBody) {

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

        List<CarDto> availableCars = fastReservationService.getAvailableCars(province, district, rentalDatetime, returnDatetime,
                                                         modelCategory,modelName);

        return ResponseEntity.ok(availableCars);
    }


    // 특정 차량 상세 정보 조회 + 반납 주차장 조회
    @GetMapping("/cars/{carId}")
    public ResponseEntity<CarDto> getCarById(@PathVariable("carId") int carId,
                                             @RequestParam("rental_datetime") String rentalDatetimeStr,
                                             @RequestParam("return_datetime") String returnDatetimeStr) {
        // String을 LocalDateTime으로 변환
        LocalDateTime rentalDatetime = LocalDateTime.parse(rentalDatetimeStr);
        LocalDateTime returnDatetime = LocalDateTime.parse(returnDatetimeStr);

        // 차량 상세 정보 조회
        CarDto carDetail = fastReservationService.getCarById(carId);

        // 가격 계산 (carId -> modelId 여야 정상 출력)
        Long totalPrice = fastReservationService.getPrice(carDetail.getModel().getModelId(), carDetail.getCarGrade(), rentalDatetime, returnDatetime);
        carDetail.setCalculatedPrice(totalPrice);

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
    public ResponseEntity<List<ParkingDto>> reserve(@RequestParam("car_id") int carId,
                                                    @RequestParam("rental_datetime") String rentalDatetimeStr,
                                                    @RequestParam("return_datetime") String returnDatetimeStr){

        // 날짜 포맷 지정
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        LocalDateTime rentalDatetime = LocalDateTime.parse(rentalDatetimeStr,formatter);
        LocalDateTime returnDatetime = LocalDateTime.parse(returnDatetimeStr,formatter);

        // 반납 가능 장소 조회
        List<ParkingDto> parkingList = fastReservationService.getParkingStation(rentalDatetime,returnDatetime,carId);
        return new ResponseEntity<>(parkingList, HttpStatus.OK);
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
