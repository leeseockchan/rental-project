package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.CarDto;
import com.road_friends.rentalcar.dto.FastReservationDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface FastReservationMapper {

    List<FastReservationDto> getAllReservations();
    FastReservationDto getReservationById(int id);
    List<CarDto> getAllCars();
    CarDto getCarById(int id);
    void reserve(FastReservationDto fastReservationDto);
    void deleteReservation(int reservationId);
    void updateReservation(FastReservationDto fastReservationDto);

    List<CarDto> getAvailableCars(@Param("province") String province,
                                  @Param("district") String district,
                                  @Param("rentalDatetime") LocalDateTime rentalDatetime,
                                  @Param("returnDatetime") LocalDateTime returnDatetime);


    int getAmountHour(int carId);

    int getAmountDay(int carId);
}
