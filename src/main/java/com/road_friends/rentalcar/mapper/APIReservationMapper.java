package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.APIReservationDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface APIReservationMapper {

    // 로그인한 사용자의 모든 예약 조회
    List<APIReservationDto> getUserReservations(Long userNum);

    // 특정 예약 상세 조회
    APIReservationDto getReservationDetail(Long reservationId);

    // 예약 수정
    int updateReservation(APIReservationDto apiReservationDto);

    // 예약 취소
    int deleteReservation(Long reservationId, Long userNum);
}
