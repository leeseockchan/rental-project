package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.CarDto;
import com.road_friends.rentalcar.dto.FastReservationDto;
import com.road_friends.rentalcar.dto.ParkingDto;
import com.road_friends.rentalcar.mapper.FastReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Transactional
public class FastReservationService {

    @Autowired
    private FastReservationMapper fastReservationMapper;

    // 모든 차량 목록 조회
    public List<CarDto> getAllCars(){
        return fastReservationMapper.getAllCars();
    }

    // 차량 정보 조회
    public CarDto getCarById(int carId) {
        return fastReservationMapper.getCarById(carId);
    }


    // 예약 목록
    public List<FastReservationDto> getReservations(){
        return fastReservationMapper.getAllReservations();
    };

    public FastReservationDto getReservationById(int id){
        return fastReservationMapper.getReservationById(id);
    }

    public void reserve(FastReservationDto fastReservationDto){


        fastReservationMapper.reserve(fastReservationDto);
    }

    public void deleteReservation(int reservationId) {
        fastReservationMapper.deleteReservation(reservationId);
    }

    public void updateReservation(FastReservationDto fastReservationDto) {
        fastReservationMapper.updateReservation(fastReservationDto);
    }


    public List<CarDto> getAvailableCars(String province, String district, LocalDateTime rentalDatetime, LocalDateTime returnDatetime,
                                         String modelCategory, String modelName) {

        return fastReservationMapper.getAvailableCars(province, district, rentalDatetime, returnDatetime,
                modelCategory,modelName);
    }


    // 가격
    public Long getPrice(FastReservationDto fastReservationDto) {
        LocalDateTime startTime = fastReservationDto.getRentalDatetime();
        LocalDateTime endTime = fastReservationDto.getReturnDatetime();

        if(startTime ==null || endTime==null){
            throw new NullPointerException("대여 시작 시간과 종료 시작은 필수 입력 값");
        }

        Long totalPrice = 0L;

        Long hoursBetween =  ChronoUnit.HOURS.between(startTime,endTime);
        Long daysBetween = ChronoUnit.DAYS.between(startTime,endTime);


        if(hoursBetween <4){
            throw new IllegalArgumentException("최소 4시간 이상 예약 가능");
        }
        if(daysBetween > 14){
            throw new IllegalArgumentException("최대 14일까지 예약 가능");
        }

        if( hoursBetween<24 ){
            // 4시간~하루 미만 예약일 때
            int hourPrice = fastReservationMapper.getAmountHour(fastReservationDto.getCarId());
            totalPrice = hourPrice * hoursBetween;
        }
        else{
            // 하루 이상 예약일 때
            int dayPrice = fastReservationMapper.getAmountDay(fastReservationDto.getCarId());
            totalPrice = dayPrice * daysBetween;
        }
        System.out.println(totalPrice);
        return totalPrice;
    }

    public List<ParkingDto> getParkingStation() {
        return fastReservationMapper.getParkingStation();
    }
}
