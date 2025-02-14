package com.road_friends.rentalcar.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ReservationMapper {

    // 가장 많이 대여한 지역 TOP 5
    @Select("SELECT rental_location, COUNT(*) AS count FROM `fast_reservation` GROUP BY rental_location ORDER BY count DESC LIMIT 5")
    List<Map<String, Object>> getTopRentalLocations();

    // 가장 많이 반납한 지역 TOP 5
    @Select("SELECT return_location, COUNT(*) AS count FROM `fast_reservation` GROUP BY return_location ORDER BY count DESC LIMIT 5")
    List<Map<String, Object>> getTopReturnLocations();

    // 가장 인기 있는 차량 TOP 5 (이 부분 추가)
    @Select("SELECT reservation_id, COUNT(*) AS count FROM `fast_reservation` GROUP BY reservation_id ORDER BY count DESC LIMIT 5")
    List<Map<String, Object>> getPopularCars();
}
