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
    public APIReservationDto getReservationDetail(int reservationSId){
        return apiReservationMapper.getReservationDetail(reservationSId);
    }

    // 이용시작
    public boolean updateRentalState(int reservationId, int currentState, int newState) {
        // 1. 예약 상태 변경 (1 → 2)
        int updatedRows = apiReservationMapper.updateRentalState(reservationId, currentState, newState);

        if (updatedRows == 0) {
            return false; // 예약 상태 변경 실패 시 종료
        }

        // 2. 해당 예약의 차량 ID 조회
        Integer carId = apiReservationMapper.getCarIdByReservationId(reservationId);

        if (carId == null) {
            return false; // 차량 ID가 없으면 종료
        }

        // 3. 차량 상태 변경 (0 → 1)
        int carUpdatedRows = apiReservationMapper.updateCarStatusToInUse(carId);

        return carUpdatedRows > 0; // 차량 상태 변경이 성공하면 true 반환
    }

    // 이용 완료
    public boolean completeRental(int reservationId) {
        // 1. 예약 상태 변경 (2 → 4)
        int updatedRows = apiReservationMapper.updateRentalState(reservationId, 2, 4);

        // 2. 차량 ID 조회
        Integer carId = apiReservationMapper.getCarIdByReservationId(reservationId);
        if (carId == null) {
            return false;
        }

        // 3. 차량 상태 업데이트 (car_status: 1 → 2 정비중)
        int carUpdatedRows = apiReservationMapper.updateCarStatus(carId, 1, 2);

        return updatedRows > 0 && carUpdatedRows > 0;
    }

    // 이용 취소
    public boolean cancelRental(int reservationId){
        // 1. 예약 상태 변경 (0 → 4)
        int updatedRows = apiReservationMapper.updateRentalState(reservationId, 0, 3);

        // 2. 차량 ID 조회
        Integer carId = apiReservationMapper.getCarIdByReservationId(reservationId);
        if (carId == null) {
            return false;
        }

        // 3. 차량 상태 업데이트 (car_status: 1 → 2 정비중)
        int carUpdatedRows = apiReservationMapper.updateCarStatus(carId, 1, 0);

        return updatedRows > 0;
    }

    // 예약 수정
    public int updateReservation(APIReservationDto apiReservationDto){
        return apiReservationMapper.updateReservation(apiReservationDto);
    }

    // 예약 취소 (삭제)
    public int deleteReservation(int reservationSId, Long userNum){
        return apiReservationMapper.deleteReservation(reservationSId, userNum);
    }
}
