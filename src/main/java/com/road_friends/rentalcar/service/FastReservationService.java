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
        fastReservationMapper.fastReserve(fastReservationDto);
    }

    public List<FastReservationDto> getReservations(){
        return fastReservationMapper.findAll();
    };


}
