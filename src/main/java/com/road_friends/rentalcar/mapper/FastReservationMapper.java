package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.CarDto;
import com.road_friends.rentalcar.dto.FastReservationDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FastReservationMapper {

    List<FastReservationDto> getAllReservations();
    FastReservationDto getReservationById(int id);
    List<CarDto> getAllCars();
    CarDto getCarById(int id);

}
