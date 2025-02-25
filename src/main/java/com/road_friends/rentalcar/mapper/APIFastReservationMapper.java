package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.FastReservationResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface APIFastReservationMapper {

    // 로그인한 사용자의 빠른예약 전체조회
    List<FastReservationResponseDto> getUserFastReservations(Long userNum);

    // 이용시작 상태 변경
    int updateRentalState(@Param("reservationId") int reservationId, @Param("newState") int newState);

    // 특정 빠른 예약 상세 조회
    FastReservationResponseDto getFastReservationDetail(Long reservationId);

    // 빠른 예약 수정
    int updateFastReservation(FastReservationResponseDto fastReservationResponseDto);

    // 빠른 예약 취소
    int deleteFastReservation(@Param("reservationId") Long reservationId, @Param("userNum") Long userNum);
}
