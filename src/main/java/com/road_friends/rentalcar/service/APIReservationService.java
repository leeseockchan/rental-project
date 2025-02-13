package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.APIReservationDto;
import com.road_friends.rentalcar.mapper.APIReservationMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class APIReservationService {

    private final APIReservationMapper apiReservationMapper;

    public APIReservationService(APIReservationMapper apiReservationMapper){
        this.apiReservationMapper = apiReservationMapper;
    }

    // 로그인한 사용자의 전체 예약 조회
    public List<APIReservationDto> getUserReservations(Long userNum){
        return apiReservationMapper.getUserReservations(userNum);
    }

    // 특정 예약 상세 조회
    public APIReservationDto getReservationDetail(Long reservationId){
        return apiReservationMapper.getReservationDetail(reservationId);
    }

    // 예약 수정
    public int updateReservation(APIReservationDto apiReservationDto){
        return apiReservationMapper.updateReservation(apiReservationDto);
    }

    // 예약 취소 (삭제)
    public int deleteReservation(Long reservationId, Long userNum){
        return apiReservationMapper.deleteReservation(reservationId, userNum);
    }
}
