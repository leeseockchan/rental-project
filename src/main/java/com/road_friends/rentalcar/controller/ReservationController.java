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

    @GetMapping("/rental-locations")
    public ResponseEntity<List<ReservationDto>> getRentalStats() {
        return ResponseEntity.ok(reservationService.getTopRentalLocations());
    }

    @GetMapping("/return-locations")
    public ResponseEntity<List<ReservationDto>> getReturnStats() {
        return ResponseEntity.ok(reservationService.getTopReturnLocations());
    }

    @GetMapping("/popular-cars")
    public ResponseEntity<List<ReservationDto>> getPopularCars() {
        return ResponseEntity.ok(reservationService.getPopularCars());
    }
}
