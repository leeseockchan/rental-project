package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.ReservationDto;
import com.road_friends.rentalcar.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stats/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    // 🚀 예약이 많은 시간대 TOP 5
    @GetMapping("/top-rental-hours")
    public ResponseEntity<List<ReservationDto>> getTopRentalHours() {
        return ResponseEntity.ok(reservationService.getTopRentalHours());
    }

    // 🚀 가장 많이 대여된 지역 TOP 5
    @GetMapping("/rental-locations")
    public ResponseEntity<List<ReservationDto>> getRentalStats() {
        return ResponseEntity.ok(reservationService.getTopRentalLocations());
    }

    // 🚀 가장 많이 반납된 지역 TOP 5
    @GetMapping("/return-locations")
    public ResponseEntity<List<ReservationDto>> getReturnStats() {
        return ResponseEntity.ok(reservationService.getTopReturnLocations());
    }

    // 🚀 가장 인기 있는 차량 TOP 5
    @GetMapping("/popular-cars")
    public ResponseEntity<List<ReservationDto>> getPopularCars() {
        return ResponseEntity.ok(reservationService.getPopularCars());
    }

    // 🚀 차량별 평균 렌트 시간 TOP 5
    @GetMapping("/car-rental-duration")
    public ResponseEntity<List<ReservationDto>> getCarRentalDuration() {
        return ResponseEntity.ok(reservationService.getTopCarRentalDuration());
    }

    // 🚀 지역별 평균 렌트 시간 TOP 5
    @GetMapping("/region-rental-duration")
    public ResponseEntity<List<ReservationDto>> getRegionRentalDuration() {
        return ResponseEntity.ok(reservationService.getTopRegionRentalDuration());
    }

    // 🚀 사용자별 평균 렌트 시간 TOP 5
    @GetMapping("/user-rental-duration")
    public ResponseEntity<List<ReservationDto>> getUserRentalDuration() {
        return ResponseEntity.ok(reservationService.getTopUserRentalDuration());
    }
}
