package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.FastReservationDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FastReservationMapper {

    void fastReserve(FastReservationDto fastReservationDto);
}
