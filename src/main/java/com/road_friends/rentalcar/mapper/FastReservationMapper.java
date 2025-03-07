package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface FastReservationMapper {

    List<FastReservationDto> getAllReservations();
    FastReservationDto getReservationById(int id);
    List<CarDto> getAllCars();
    CarDto getCarById(int id);

    void reserve(FastReservationDto fastReservationDto);

    void updateFastReservationId(@Param("reservationId") int reservationId,
                                 @Param("fastReservationId") int fastReservationId);

    void deleteReservation(int reservationId);
    void updateReservation(FastReservationDto fastReservationDto);

    List<CarDto> getAvailableCars(@Param("province") String province,
                                  @Param("district") String district,
                                  @Param("rentalDatetime") LocalDateTime rentalDatetime,
                                  @Param("returnDatetime") LocalDateTime returnDatetime,
                                  @Param("modelCategory") String modelCategory,
                                  @Param("modelName") String modelName,
                                  @Param("endPrice") Integer endPrice
    );



    int getAmountHour(int carId);

    int getAmountDay(int carId);

    List<CarDto> searchAvailableCars(String province, String district, LocalDateTime rentalDatetime, LocalDateTime returnDateTime,
                                     String modelCategory, String modelName);

    // 동일 도시 내의 주차장 조회 (4시간)
    List<ParkingDto> getParkingStationBelow4hours(int carId);
    // 모든 주차장 조회
    List<ParkingDto> getAllParkingStation(int carId);


    ModelDto getModelById(int modelId);

    void insertReservation(ReservationDto reservationDto);
    int getLastInsertId();

    void updateCarStatusTo0(int carId);

    @Update("UPDATE fast_reservation SET rental_state = 1 WHERE reservation_id = #{reservationId}")
    void updateRentalState(@Param("reservationId") int reservationId);
}
