package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.FastReservationDto;
import com.road_friends.rentalcar.mapper.FastReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FastReservationService {

    @Autowired
    private FastReservationMapper fastReservationMapper;

    public void fastReserve(FastReservationDto fastReservationDto){
        fastReservationMapper.fastReserve(fastReservationDto);
    }
}
