package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.FastReservationDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FastReservationMapper {

    void create(FastReservationDto fastReservationDto);

    List<FastReservationDto> findAll();

    FastReservationDto findById(int id);

    void delete(int id);

    void update(FastReservationDto fastReservationDto);

}
