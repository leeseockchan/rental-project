package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.FastReservationDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FastReservationMapper {

    void fastReserve(FastReservationDto fastReservationDto);

    List<FastReservationDto> findAll();

    FastReservationDto getReservationById(int id);


}
