package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.FastReservationResponseDto;
import com.road_friends.rentalcar.mapper.APIFastReservationMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class APIFastReservationService {

    private final APIFastReservationMapper apiFastReservationMapper;

    public APIFastReservationService(APIFastReservationMapper apiFastReservationMapper){
        this.apiFastReservationMapper = apiFastReservationMapper;
    }

    // 이용시작
    public boolean updateRentalState(int reservationId, int newState) {
        return apiFastReservationMapper.updateRentalState(reservationId, newState) > 0;
    }

    // 로그인한 사용자의 빠른 예약 전체 조회
    public List<FastReservationResponseDto> getUserFastReservations(Long userId) {
        return apiFastReservationMapper.getUserFastReservations(userId);
    }

    // 특정 예약 상세 조회
    public FastReservationResponseDto getFastReservationDetail(Long reservationId){
        return apiFastReservationMapper.getFastReservationDetail(reservationId);
    }

    // 빠른 예약 수정
    public int updateFastReservation(FastReservationResponseDto fastReservationResponseDto){
        return apiFastReservationMapper.updateFastReservation(fastReservationResponseDto);
    }

    // 빠른 예약 취소
    public int deleteFastReservation(Long reservationId, Long userNum){
        return apiFastReservationMapper.deleteFastReservation(reservationId, userNum);
    }
}
