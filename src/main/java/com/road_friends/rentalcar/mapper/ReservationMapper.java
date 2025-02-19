package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.DataPoint;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface ReservationMapper {

    // 예약이 많은 시간대 TOP 5
    @Select("""
        SELECT HOUR(rental_datetime) AS label, COUNT(*) AS count
        FROM fast_reservation
        GROUP BY HOUR(rental_datetime)
        ORDER BY count DESC
        LIMIT 5
    """)
    List<DataPoint> getTopRentalHours();

    // 가장 많이 대여된 지역 TOP 5
    @Select("""
        SELECT rental_location AS label, COUNT(*) AS count
        FROM fast_reservation
        GROUP BY rental_location
        ORDER BY count DESC
        LIMIT 5
    """)
    List<DataPoint> getTopRentalLocations();

    // 가장 많이 반납된 지역 TOP 5
    @Select("""
        SELECT return_location AS label, COUNT(*) AS count
        FROM fast_reservation
        WHERE return_location IS NOT NULL
        GROUP BY return_location
        ORDER BY count DESC
        LIMIT 5
    """)
    List<DataPoint> getTopReturnLocations();

    // 가장 인기 있는 차량 TOP 5
    @Select("""
        SELECT car_id AS label, COUNT(*) AS count
        FROM fast_reservation
        GROUP BY car_id
        ORDER BY count DESC
        LIMIT 5
    """)
    List<DataPoint> getPopularCars();

    // 평균 렌트 시간 TOP 5
    @Select("""
        SELECT car_id AS label, AVG(TIMESTAMPDIFF(HOUR, rental_datetime, return_datetime)) AS count
        FROM fast_reservation
        WHERE return_datetime IS NOT NULL
        GROUP BY car_id
        ORDER BY count DESC
        LIMIT 5
    """)
    List<DataPoint> getAverageRentalDurations();
}
