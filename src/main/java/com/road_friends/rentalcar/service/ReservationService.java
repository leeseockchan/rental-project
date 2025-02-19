package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.DataPoint;
import com.road_friends.rentalcar.dto.ReservationDto;
import com.road_friends.rentalcar.mapper.ReservationMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final ReservationMapper reservationMapper;

    public ReservationService(ReservationMapper reservationMapper) {
        this.reservationMapper = reservationMapper;
    }

    // 🚀 DataPoint → ReservationDto 변환 메서드 추가
    private List<ReservationDto> convertToDto(List<DataPoint> dataPoints) {
        return dataPoints.stream()
                .map(dp -> new ReservationDto(dp.getLabel(), dp.getCount()))
                .collect(Collectors.toList());
    }

    public List<ReservationDto> getTopRentalLocations() {
        return convertToDto(reservationMapper.getTopRentalLocations());
    }

    public List<ReservationDto> getTopReturnLocations() {
        return convertToDto(reservationMapper.getTopReturnLocations());
    }

    public List<ReservationDto> getPopularCars() {
        return convertToDto(reservationMapper.getPopularCars());
    }

    public List<ReservationDto> getAverageRentalDurations() {
        return convertToDto(reservationMapper.getAverageRentalDurations());
    }

    public List<ReservationDto> getTopRentalHours() {
        return convertToDto(reservationMapper.getTopRentalHours());
    }
}
