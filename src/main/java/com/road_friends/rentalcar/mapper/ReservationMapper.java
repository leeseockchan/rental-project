package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.ReservationDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ReservationMapper {

    // 가장 많이 대여한 지역 TOP 5
    @Select("SELECT rental_location AS label, COUNT(*) AS count " +
            "FROM fast_reservation " +
            "GROUP BY rental_location " +
            "ORDER BY count DESC LIMIT 5")
    List<ReservationDto> getTopRentalLocations();

    // 가장 많이 반납한 지역 TOP 5
    @Select("SELECT return_location AS label, COUNT(*) AS count " +
            "FROM fast_reservation " +
            "GROUP BY return_location " +
            "ORDER BY count DESC LIMIT 5")
    List<ReservationDto> getTopReturnLocations();

    // 가장 인기 있는 차량 TOP 5
    @Select("SELECT c.car_name AS label, COUNT(*) AS count " +
            "FROM fast_reservation f " +
            "JOIN car_data c ON f.car_id = c.car_id " +
            "GROUP BY c.car_name " +
            "ORDER BY count DESC LIMIT 5")
    List<ReservationDto> getPopularCars();
}
