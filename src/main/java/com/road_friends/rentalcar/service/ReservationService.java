package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.mapper.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationMapper reservationMapper;

    public List<Map<String, Object>> getTopRentalLocations() {
        return reservationMapper.getTopRentalLocations();
    }

    public List<Map<String, Object>> getTopReturnLocations() {
        return reservationMapper.getTopReturnLocations();
    }

    public List<Map<String, Object>> getPopularCars() {
        return reservationMapper.getPopularCars();
    }
}
