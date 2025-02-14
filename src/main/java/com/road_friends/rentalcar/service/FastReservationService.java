package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.FastReservationDto;
import com.road_friends.rentalcar.mapper.FastReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FastReservationService {

    @Autowired
    private FastReservationMapper fastReservationMapper;

    // 빠른 예약
    public void fastReserve(FastReservationDto fastReservationDto){
        fastReservationMapper.create(fastReservationDto);
    }

    // 예약 목록
    public List<FastReservationDto> getReservations(){
        return fastReservationMapper.findAll();
    };

    // 예약 상세
    public FastReservationDto getReservationById(int id){
        return fastReservationMapper.findById(id);
    }

    // 예약 삭제
    public void deleteReservation(int id){
        fastReservationMapper.delete(id);
    }

    public void updateReservation(FastReservationDto fastReservationDto){
        fastReservationMapper.update(fastReservationDto);
    }
}
