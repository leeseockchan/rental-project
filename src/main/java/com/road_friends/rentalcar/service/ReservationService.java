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

    // 🚀 예약이 많은 시간대 TOP 5 (빠른 예약)
    public List<ReservationDto> getTopFastRentalHours() {
        return convertToDto(reservationMapper.getTopFastRentalHours());
    }

    // 🚀 예약이 많은 시간대 TOP 5 (단기 예약)
    public List<ReservationDto> getTopShortRentalHours() {
        return convertToDto(reservationMapper.getTopShortRentalHours());
    }

    // 🚀 가장 많이 대여된 지역 TOP 5 (빠른 예약)
    public List<ReservationDto> getTopFastRentalLocations() {
        return convertToDto(reservationMapper.getTopFastRentalLocations());
    }

//    // 🚀 가장 많이 대여된 지역 TOP 5 (단기 예약)
//    public List<ReservationDto> getTopShortRentalLocations() {
//        return convertToDto(reservationMapper.getTopShortRentalLocations());
//    }

    // 🚀 가장 많이 반납된 지역 TOP 5 (빠른 예약)
    public List<ReservationDto> getTopFastReturnLocations() {
        return convertToDto(reservationMapper.getTopFastReturnLocations());
    }

//    // 🚀 가장 많이 반납된 지역 TOP 5 (단기 예약)
//    public List<ReservationDto> getTopShortReturnLocations() {
//        return convertToDto(reservationMapper.getTopShortReturnLocations());
//    }

    // 🚀 가장 인기 있는 차량 TOP 5 (빠른 예약)
    public List<ReservationDto> getTopFastPopularCars() {
        return convertToDto(reservationMapper.getTopFastPopularCars());
    }

    // 🚀 가장 인기 있는 차량 TOP 5 (단기 예약)
    public List<ReservationDto> getTopShortPopularCars() {
        return convertToDto(reservationMapper.getTopShortPopularCars());
    }

    // 🚀 차량별 평균 렌트 시간 TOP 5 (빠른 예약)
    public List<ReservationDto> getTopFastCarRentalDuration() {
        return convertToDto(reservationMapper.getTopFastCarRentalDuration());
    }

    // 🚀 차량별 평균 렌트 시간 TOP 5 (단기 예약)
    public List<ReservationDto> getTopShortCarRentalDuration() {
        return convertToDto(reservationMapper.getTopShortCarRentalDuration());
    }

    // 🚀 지역별 평균 렌트 시간 TOP 5 (빠른 예약)
    public List<ReservationDto> getTopFastRegionRentalDuration() {
        return convertToDto(reservationMapper.getTopFastRegionRentalDuration());
    }

//    // 🚀 지역별 평균 렌트 시간 TOP 5 (단기 예약)
//    public List<ReservationDto> getTopShortRegionRentalDuration() {
//        return convertToDto(reservationMapper.getTopShortRegionRentalDuration());
//    }

    // 🚀 사용자별 평균 렌트 시간 TOP 5 (빠른 예약)
    public List<ReservationDto> getTopFastUserRentalDuration() {
        return convertToDto(reservationMapper.getTopFastUserRentalDuration());
    }

    // 🚀 사용자별 평균 렌트 시간 TOP 5 (단기 예약)
    public List<ReservationDto> getTopShortUserRentalDuration() {
        return convertToDto(reservationMapper.getTopShortUserRentalDuration());
    }

    // 가장 인기 있는 차량 TOP 5
    public List<DataPoint> getTopPopularCars() {
        return reservationMapper.getTopPopularCars();
    }
}
