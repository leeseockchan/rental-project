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
    public boolean updateRentalState(int reservationId, int currentState, int newState) {
        // 1. 예약 상태 변경 (1 → 2)
        int updatedRows = apiFastReservationMapper.updateRentalState(reservationId, currentState, newState);

        if (updatedRows == 0) {
            return false; // 예약 상태 변경 실패 시 종료
        }

        // 2. 해당 예약의 차량 ID 조회
        Integer carId = apiFastReservationMapper.getCarIdByReservationId(reservationId);

        if (carId == null) {
            return false; // 차량 ID가 없으면 종료
        }

        // 3. 차량 상태 변경 (0 → 1)
        int carUpdatedRows = apiFastReservationMapper.updateCarStatusToInUse(carId);

        return carUpdatedRows > 0; // 차량 상태 변경이 성공하면 true 반환
    }


    // 이용 완료
    public boolean completeRental(int reservationId) {
        // 1. 예약 상태 변경 (2 → 4)
        int updatedRows = apiFastReservationMapper.updateRentalState(reservationId, 2, 4);

        // 2. 차량 ID 조회
        Integer carId = apiFastReservationMapper.getCarIdByReservationId(reservationId);
        if (carId == null) {
            return false;
        }

        // 3. 차량 상태 업데이트 (car_status: 1 → 2 정비중)
        int carUpdatedRows = apiFastReservationMapper.updateCarStatus(carId, 1, 2);

        return updatedRows > 0 && carUpdatedRows > 0;
    }

    // 이용 취소
    public boolean cancelRental(int reservationId){
        // 1. 예약 상태 변경 (0 → 4)
        int updatedRows = apiFastReservationMapper.updateRentalState(reservationId, 0, 3);

        // 2. 차량 ID 조회
        Integer carId = apiFastReservationMapper.getCarIdByReservationId(reservationId);
        if (carId == null) {
            return false;
        }

        // 3. 차량 상태 업데이트 (car_status: 1 → 0)
        int carUpdatedRows = apiFastReservationMapper.updateCarStatus(carId, 1, 0);

        return updatedRows > 0 && carUpdatedRows > 0;
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
