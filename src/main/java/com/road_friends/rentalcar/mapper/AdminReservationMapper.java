package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.AdminCarDto;
import com.road_friends.rentalcar.dto.FastReservationDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminReservationMapper {

  // 전체 예약 목록 조회
  @Select("""
        SELECT 
            r.reservation_id, r.car_id, 
            m.model_name AS model_name,  -- 모델명 가져오기
            p1.parking_name AS rental_location_name, 
            p2.parking_name AS return_location_name,
            r.rental_datetime, r.return_datetime, r.rental_state
        FROM fast_reservation r
        LEFT JOIN car c ON r.car_id = c.car_id
        LEFT JOIN model m ON c.model_id = m.model_id  -- 모델 테이블 조인
        LEFT JOIN parking p1 ON r.rental_location = p1.parking_id
        LEFT JOIN parking p2 ON r.return_location = p2.parking_id
        ORDER BY r.reservation_id DESC
    """)
  List<FastReservationDto> findAllReservations();

  // 개별 예약 상세 조회
  @Select("""
    SELECT 
        r.reservation_id, r.car_id, 
        m.model_name AS model_name,  -- 모델명 추가
        p1.parking_name AS rental_location_name, 
        p2.parking_name AS return_location_name,
        r.rental_datetime, r.return_datetime, r.rental_state
    FROM fast_reservation r
    LEFT JOIN car c ON r.car_id = c.car_id
    LEFT JOIN model m ON c.model_id = m.model_id  -- 모델 테이블 조인
    LEFT JOIN parking p1 ON r.rental_location = p1.parking_id
    LEFT JOIN parking p2 ON r.return_location = p2.parking_id
    WHERE r.reservation_id = #{reservationId}
""")
  FastReservationDto findReservationById(@Param("reservationId") int reservationId);

  @Delete("DELETE FROM fast_reservation WHERE reservation_id = #{reservationId}")
  void deleteReservation(@Param("reservationId") int reservationId);

  // 에약 상태 수정
  @Update("UPDATE fast_reservation SET rental_state = #{rentalState} WHERE reservation_id = #{reservationId}")
  void updateRentalState(@Param("reservationId") int reservationId, @Param("rentalState") int rentalState);

  //정비중인 차량 조회
  @Select("""
    SELECT 
        c.car_id, c.model_id, 
        m.model_name AS model_name, 
        c.car_status, c.car_year, c.car_fuel, c.car_grade
    FROM car c
    LEFT JOIN model m ON c.model_id = m.model_id
    WHERE c.car_status = 2  -- 정비 중인 차량만 조회
""")
  List<AdminCarDto> findMaintenanceCars();

  // 정비 상태 변경
  @Update("UPDATE car SET car_status = 0 WHERE car_id = #{carId}")
  void updateCarStatusToAvailable(@Param("carId") int carId);

  //정비중인 차량 상세 정보 조회
  @Select("""
    SELECT 
        c.car_id, c.model_id, 
        m.model_name AS model_name, 
        c.car_category, c.car_status, c.car_year, c.car_fuel, c.car_grade, c.car_options, 
        p.parking_name AS rental_station_name
    FROM car c
    LEFT JOIN model m ON c.model_id = m.model_id
    LEFT JOIN parking p ON c.rental_station = p.parking_id
    WHERE c.car_id = #{carId} AND c.car_status = 2
""")
  AdminCarDto findMaintenanceCarById(@Param("carId") int carId);

}


