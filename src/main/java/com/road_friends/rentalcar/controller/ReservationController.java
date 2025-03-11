package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.DataPoint;
import com.road_friends.rentalcar.dto.DashboardReservationDto;
import com.road_friends.rentalcar.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stats/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    // 🚀 예약이 많은 시간대 TOP 5 (빠른 예약)
    @GetMapping("/top-fast-rental-hours")
    public ResponseEntity<List<DashboardReservationDto>> getTopFastRentalHours() {
        return ResponseEntity.ok(reservationService.getTopFastRentalHours());
    }

    // 🚀 예약이 많은 시간대 TOP 5 (단기 예약)
    @GetMapping("/top-short-rental-hours")
    public ResponseEntity<List<DashboardReservationDto>> getTopShortRentalHours() {
        return ResponseEntity.ok(reservationService.getTopShortRentalHours());
    }

    // 🚀 가장 많이 대여된 지역 TOP 5 (빠른 예약)
    @GetMapping("/top-fast-rental-locations")
    public ResponseEntity<List<DashboardReservationDto>> getTopFastRentalLocations() {
        return ResponseEntity.ok(reservationService.getTopFastRentalLocations());
    }

    // 🚀 가장 많이 대여된 지역 TOP 5 (단기 예약)
    @GetMapping("/top-short-rental-locations")
    public ResponseEntity<List<DashboardReservationDto>> getTopShortRentalLocations() {
        return ResponseEntity.ok(reservationService.getTopShortRentalLocations());
    }

    // 🚀 가장 많이 반납된 지역 TOP 5 (빠른 예약)
    @GetMapping("/top-fast-return-locations")
    public ResponseEntity<List<DashboardReservationDto>> getTopFastReturnLocations() {
        return ResponseEntity.ok(reservationService.getTopFastReturnLocations());
    }

    // 🚀 가장 많이 반납된 지역 TOP 5 (단기 예약)
    @GetMapping("/top-short-return-locations")
    public ResponseEntity<List<DashboardReservationDto>> getTopShortReturnLocations() {
        return ResponseEntity.ok(reservationService.getTopShortReturnLocations());
    }

    // 🚀 가장 인기 있는 차량 TOP 5 (빠른 예약)
    @GetMapping("/top-fast-popular-cars")
    public ResponseEntity<List<DashboardReservationDto>> getTopFastPopularCars() {
        return ResponseEntity.ok(reservationService.getTopFastPopularCars());
    }

    // 🚀 가장 인기 있는 차량 TOP 5 (단기 예약)
    @GetMapping("/top-short-popular-cars")
    public ResponseEntity<List<DashboardReservationDto>> getTopShortPopularCars() {
        return ResponseEntity.ok(reservationService.getTopShortPopularCars());
    }

    // 🚀 차량별 평균 렌트 시간 TOP 5 (빠른 예약)
    @GetMapping("/top-fast-car-rental-duration")
    public ResponseEntity<List<DashboardReservationDto>> getTopFastCarRentalDuration() {
        return ResponseEntity.ok(reservationService.getTopFastCarRentalDuration());
    }

    // 🚀 차량별 평균 렌트 시간 TOP 5 (단기 예약)
    @GetMapping("/top-short-car-rental-duration")
    public ResponseEntity<List<DashboardReservationDto>> getTopShortCarRentalDuration() {
        return ResponseEntity.ok(reservationService.getTopShortCarRentalDuration());
    }

    // 🚀 지역별 평균 렌트 시간 TOP 5 (빠른 예약)
    @GetMapping("/top-fast-region-rental-duration")
    public ResponseEntity<List<DashboardReservationDto>> getTopFastRegionRentalDuration() {
        return ResponseEntity.ok(reservationService.getTopFastRegionRentalDuration());
    }

    // 🚀 지역별 평균 렌트 시간 TOP 5 (단기 예약)
    @GetMapping("/top-short-region-rental-duration")
    public ResponseEntity<List<DashboardReservationDto>> getTopShortRegionRentalDuration() {
        return ResponseEntity.ok(reservationService.getTopShortRegionRentalDuration());
    }

    // 🚀 사용자별 평균 렌트 시간 TOP 5 (빠른 예약)
    @GetMapping("/top-fast-user-rental-duration")
    public ResponseEntity<List<DashboardReservationDto>> getTopFastUserRentalDuration() {
        return ResponseEntity.ok(reservationService.getTopFastUserRentalDuration());
    }

    // 🚀 사용자별 평균 렌트 시간 TOP 5 (단기 예약)
    @GetMapping("/top-short-user-rental-duration")
    public ResponseEntity<List<DashboardReservationDto>> getTopShortUserRentalDuration() {
        return ResponseEntity.ok(reservationService.getTopShortUserRentalDuration());
    }

    // 🚗 가장 인기 있는 차량 TOP 5
    @GetMapping("/popular-cars")
    public List<DataPoint> getTopAllPopularCars() {
        return reservationService.getTopAllPopularCars();
    }
}
