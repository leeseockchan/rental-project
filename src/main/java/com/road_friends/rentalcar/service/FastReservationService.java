package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.CarDto;
import com.road_friends.rentalcar.dto.FastReservationDto;
import com.road_friends.rentalcar.mapper.FastReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

}
