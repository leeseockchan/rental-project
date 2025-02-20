package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.DataPoint;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface ReservationMapper {

    // 예약이 많은 시간대 TOP 5 (빠른 예약)
    @Select("""
    SELECT HOUR(rental_datetime) AS label, COUNT(*) AS count
    FROM fast_reservation
    GROUP BY HOUR(rental_datetime)
    ORDER BY count DESC
    LIMIT 5
    """)
    List<DataPoint> getTopFastRentalHours();

    // 예약이 많은 시간대 TOP 5 (단기 예약)
    @Select("""
    SELECT HOUR(reservation_s_start_date) AS label, COUNT(*) AS count
    FROM short_reservation
    GROUP BY HOUR(reservation_s_start_date)
    ORDER BY count DESC
    LIMIT 5
    """)
    List<DataPoint> getTopShortRentalHours();

    // 가장 많이 대여된 지역 TOP 5 (빠른 예약)
    @Select("""
    SELECT CONCAT(p.parking_province, ' ', p.parking_district) AS label, COUNT(*) AS count
    FROM fast_reservation fr
    JOIN parking p ON fr.rental_location = p.parking_id
    GROUP BY p.parking_province, p.parking_district
    ORDER BY count DESC
    LIMIT 5
    """)
    List<DataPoint> getTopFastRentalLocations();

    // 가장 많이 대여된 지역 TOP 5 (단기 예약)

    // 가장 많이 반납된 지역 TOP 5 (빠른 예약)
    @Select("""
    SELECT CONCAT(p.parking_province, ' ', p.parking_district) AS label, COUNT(*) AS count
    FROM fast_reservation fr
    JOIN parking p ON fr.return_location = p.parking_id
    WHERE fr.return_location IS NOT NULL
    GROUP BY p.parking_province, p.parking_district
    ORDER BY count DESC
    LIMIT 5
    """)
    List<DataPoint> getTopFastReturnLocations();

    // 가장 많이 반납된 지역 TOP 5 (단기 예약)

    // 가장 인기 있는 차량 TOP 5 (빠른 예약)
    @Select("""
    SELECT m.model_name AS label, COUNT(*) AS count
    FROM fast_reservation fr
    JOIN car c ON fr.car_id = c.car_id
    JOIN model m ON c.model_id = m.model_id
    GROUP BY m.model_name
    ORDER BY count DESC
    LIMIT 5
    """)
    List<DataPoint> getTopFastPopularCars();

    // 가장 인기 있는 차량 TOP 5 (단기 예약)
    @Select("""
    SELECT m.model_name AS label, COUNT(*) AS count
    FROM short_reservation sr
    JOIN car c ON sr.car_id = c.car_id
    JOIN model m ON c.model_id = m.model_id
    GROUP BY m.model_name
    ORDER BY count DESC
    LIMIT 5
    """)
    List<DataPoint> getTopShortPopularCars();

    // 차량별 평균 렌트 시간 TOP 5 (빠른 예약)
    @Select("""
    SELECT m.model_name AS label, AVG(TIMESTAMPDIFF(HOUR, fr.rental_datetime, fr.return_datetime)) AS count
    FROM fast_reservation fr
    JOIN car c ON fr.car_id = c.car_id
    JOIN model m ON c.model_id = m.model_id
    WHERE fr.return_datetime IS NOT NULL
    GROUP BY m.model_name
    ORDER BY count DESC
    LIMIT 5
    """)
    List<DataPoint> getTopFastCarRentalDuration();

    // 차량별 평균 렌트 시간 TOP 5 (단기 예약)
    @Select("""
    SELECT m.model_name AS label, AVG(TIMESTAMPDIFF(HOUR, sr.reservation_s_start_date, sr.reservation_s_end_date)) AS count
    FROM short_reservation sr
    JOIN car c ON sr.car_id = c.car_id
    JOIN model m ON c.model_id = m.model_id
    WHERE sr.reservation_s_end_date IS NOT NULL
    GROUP BY m.model_name
    ORDER BY count DESC
    LIMIT 5
    """)
    List<DataPoint> getTopShortCarRentalDuration();

    // 지역별 평균 렌트 시간 TOP 5 (빠른 예약)
    @Select("""
    SELECT CONCAT(p.parking_province, ' ', p.parking_district) AS label,
           AVG(TIMESTAMPDIFF(HOUR, fr.rental_datetime, fr.return_datetime)) AS count
    FROM fast_reservation fr
    JOIN parking p ON fr.rental_location = p.parking_id
    WHERE fr.return_datetime IS NOT NULL
    GROUP BY p.parking_province, p.parking_district
    ORDER BY count DESC
    LIMIT 5
    """)
    List<DataPoint> getTopFastRegionRentalDuration();

    // 지역별 평균 렌트 시간 TOP 5 (단기 예약)

    // 사용자별 평균 렌트 시간 TOP 5 (빠른 예약)
    @Select("""
    SELECT u.user_name AS label,
           AVG(TIMESTAMPDIFF(HOUR, fr.rental_datetime, fr.return_datetime)) AS count
    FROM fast_reservation fr
    JOIN user u ON fr.user_num = u.user_num
    WHERE fr.return_datetime IS NOT NULL
    GROUP BY u.user_name
    ORDER BY count DESC
    LIMIT 5
    """)
    List<DataPoint> getTopFastUserRentalDuration();

    // 사용자별 평균 렌트 시간 TOP 5 (단기 예약)
    @Select("""
    SELECT u.user_name AS label,
           AVG(TIMESTAMPDIFF(HOUR, sr.reservation_s_start_date, sr.reservation_s_end_date)) AS count
    FROM short_reservation sr
    JOIN user u ON sr.user_num = u.user_num
    WHERE sr.reservation_s_end_date IS NOT NULL
    GROUP BY u.user_name
    ORDER BY count DESC
    LIMIT 5
    """)
    List<DataPoint> getTopShortUserRentalDuration();

    // 가장 인기 있는 차량 TOP 5
    @Select("""
    SELECT m.model_name AS label, COUNT(*) AS count
    FROM fast_reservation fr
    JOIN car c ON fr.car_id = c.car_id
    JOIN model m ON c.model_id = m.model_id
    GROUP BY m.model_name
    ORDER BY count DESC
    LIMIT 5
    """)
    List<DataPoint> getTopPopularCars();


}
