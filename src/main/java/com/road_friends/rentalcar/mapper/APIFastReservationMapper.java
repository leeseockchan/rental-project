package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.APIFastReservationDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface APIFastReservationMapper {

    // 로그인한 사용자의 빠른예약 전체조회
    List<APIFastReservationDto> getUserFastReservations(Long userNum);

    // 특정 빠른 예약 상세 조회
    APIFastReservationDto getFastReservationDetail(Long reservationId);

    // 빠른 예약 수정
    int updateFastReservation(APIFastReservationDto apiFastReservationDto);

    // 빠른 예약 취소
    int deleteFastReservation(Long reservationId, Long userNum);
}
