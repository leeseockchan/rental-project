package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.ReservationDto;
import com.road_friends.rentalcar.mapper.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationMapper reservationMapper;

    public List<ReservationDto> getAverageRentalDurations() {
        return reservationMapper.getAverageRentalDurations();
    }

    public List<ReservationDto> getTopRentalLocations() {
        return reservationMapper.getTopRentalLocations();
    }

    public List<ReservationDto> getTopReturnLocations() {
        return reservationMapper.getTopReturnLocations();
    }

    public List<ReservationDto> getPopularCars() {
        return reservationMapper.getPopularCars();
    }
}
