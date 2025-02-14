package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/stats/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping("/rental-locations")
    public ResponseEntity<List<Map<String, Object>>> getRentalStats(){
        return ResponseEntity.ok(reservationService.getTopRentalLocations());
    }

    @GetMapping("/return-locations")
    public ResponseEntity<List<Map<String, Object>>> getReturnStats() {
        return ResponseEntity.ok(reservationService.getTopReturnLocations());
    }

    @GetMapping("/popular-cars")
    public ResponseEntity<List<Map<String, Object>>> getPopularCars() {
        return ResponseEntity.ok(reservationService.getPopularCars());
    }


}
