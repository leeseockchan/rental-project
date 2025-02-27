package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.AdminCarDto;
import com.road_friends.rentalcar.dto.FastReservationDto;
import com.road_friends.rentalcar.mapper.AdminReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminReservationService {

  @Autowired
  private AdminReservationMapper adminReservationMapper;

  // 전체 예약 목록 조회
  public List<FastReservationDto> findAllReservations() {
    return adminReservationMapper.findAllReservations();
  }

  // 개별 예약 상세 조회
  public FastReservationDto findReservationById(int reservationId) {
    return adminReservationMapper.findReservationById(reservationId);
  }

  // 삭제
  public void deleteReservation(int reservationId) {
    adminReservationMapper.deleteReservation(reservationId);
  }

  // 예약 상태 수정
  public void updateRentalState(int reservationId, int rentalState) {
    adminReservationMapper.updateRentalState(reservationId, rentalState);
  }

}
