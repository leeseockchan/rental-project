package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.CarDto;
import com.road_friends.rentalcar.dto.FastReservationDto;
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


    public List<CarDto> getAvailableCars(String province, String district, LocalDateTime rentalDatetime, LocalDateTime returnDateTime) {

        return fastReservationMapper.getAvailableCars(province,district,rentalDatetime, returnDateTime);
    }


    // 가격
    public Long getPrice(FastReservationDto fastReservationDto) {
        LocalDateTime startTime = fastReservationDto.getRentalDatetime();
        LocalDateTime endTime = fastReservationDto.getReturnDatetime();

        Long totalPrice = 0L;

        Long hoursBetween =  ChronoUnit.HOURS.between(startTime,endTime);
        Long daysBetween = ChronoUnit.DAYS.between(startTime,endTime);

        if( daysBetween<1 && hoursBetween<24 && hoursBetween>=4  ){
            // 4시간~하루 미만 예약일 때
            int hourPrice = fastReservationMapper.getAmountHour(fastReservationDto.getCarId());
            totalPrice = hourPrice * hoursBetween;
            System.out.println(hourPrice);
            
        }
        else if(daysBetween>=1 &&daysBetween<=14){
            // 하루 이상 예약일 때
            int dayPrice = fastReservationMapper.getAmountDay(fastReservationDto.getCarId());
            totalPrice = dayPrice * daysBetween;
            System.out.println(dayPrice);
        }
        else if(daysBetween<1 && hoursBetween<24 && hoursBetween<4){
            System.out.println("4시간 이상 예약 가능");
        }
        return totalPrice;
    }
}
