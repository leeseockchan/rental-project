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

    // 기존 메서드들
    public List<ReservationDto> getTopRentalLocations() {
        return convertToDto(reservationMapper.getTopRentalLocations());
    }

    public List<ReservationDto> getTopReturnLocations() {
        return convertToDto(reservationMapper.getTopReturnLocations());
    }

    public List<ReservationDto> getPopularCars() {
        return convertToDto(reservationMapper.getPopularCars());
    }

    public List<ReservationDto> getTopRentalHours() {
        return convertToDto(reservationMapper.getTopRentalHours());
    }

    // 새로운 메서드들 추가
    public List<ReservationDto> getTopCarRentalDuration() {
        return convertToDto(reservationMapper.getTopCarRentalDuration());
    }

    public List<ReservationDto> getTopRegionRentalDuration() {
        return convertToDto(reservationMapper.getTopRegionRentalDuration());
    }

    public List<ReservationDto> getTopUserRentalDuration() {
        return convertToDto(reservationMapper.getTopUserRentalDuration());
    }
}
