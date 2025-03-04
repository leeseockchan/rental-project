package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.APIReservationDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface APIReservationMapper {

    // 로그인한 사용자의 모든 예약 조회
    List<APIReservationDto> getUserReservations(Long userNum);

    // 이용시작 상태 변경
    int updateRentalState(@Param("reservationId") int reservationId,
                          @Param("currentState") int currentState,
                          @Param("newState") int newState);

    Integer getCarIdByReservationId(@Param("reservationId") int reservationId);

    // 이용 시작 차량 상태 변경
    int updateCarStatusToInUse(@Param("carId") int carId);

    // 이용완료 자동차 상태 변경
    int updateCarStatus(@Param("carId") int carId,
                        @Param("currentStatus") int currentStatus,
                        @Param("newStatus") int newStatus);
    // 특정 예약 상세 조회
    APIReservationDto getReservationDetail(int reservationSId);

    // 예약 수정
    int updateReservation(APIReservationDto apiReservationDto);

    // 예약 취소
    int deleteReservation(@Param("reservationSId") int reservationSId, @Param("userNum") Long userNum);
}
