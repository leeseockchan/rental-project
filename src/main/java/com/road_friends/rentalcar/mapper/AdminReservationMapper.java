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

}
