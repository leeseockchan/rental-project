package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.FastReservationResponseDto;
import com.road_friends.rentalcar.service.APIFastReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fast/reservations")
public class APIFastReservationController {

    private final APIFastReservationService apiFastReservationService;

    public APIFastReservationController(APIFastReservationService apiFastReservationService){
        this.apiFastReservationService = apiFastReservationService;
    }

    // 로그인한 사용자의 빠른 예약 전체 조회
    @GetMapping
    public List<FastReservationResponseDto> getUserFastReservations(@RequestParam("userNum") Long userNum){
        return  apiFastReservationService.getUserFastReservations(userNum);
    }

    // 특정 빠른 예약 상세 조회
    @GetMapping("/{reservationId}")
    public FastReservationResponseDto getFastReservationDetail(@PathVariable("reservationId") Long reservationId){
        return apiFastReservationService.getFastReservationDetail(reservationId);
    }

    // 이용 시작 (1 → 2)
    @PutMapping("/{reservationId}/start")
    public ResponseEntity<String> startRental(@PathVariable int reservationId) {
        boolean isUpdated = apiFastReservationService.updateRentalState(reservationId, 1, 2); // 현재 상태 1 → 2 변경
        return isUpdated ? ResponseEntity.ok("이용이 시작되었습니다.") : ResponseEntity.badRequest().body("이용 시작 실패");
    }

    // 이용 완료 (2 → 4)
    @PutMapping("/{reservationId}/complete")
    public ResponseEntity<String> completeRental(@PathVariable int reservationId) {
        boolean isUpdated = apiFastReservationService.completeRental(reservationId);
        return isUpdated ? ResponseEntity.ok("이용이 완료되었습니다.") : ResponseEntity.badRequest().body("이용 완료 실패");
    }

    // 예약취소 관리
    @PutMapping("/{reservationId}/cancel")
    public ResponseEntity<String> cancelRental(@PathVariable int reservationId) {
        boolean isUpdated = apiFastReservationService.cancelRental(reservationId);
        return isUpdated ? ResponseEntity.ok("예약이 취소되었습니다.") : ResponseEntity.badRequest().body("예약 취소 실패");
    }


    // 예약 정보 수정
    @PutMapping("/{reservationId}")
    public int updateFastReservation(@PathVariable("reservationId") int reservationId, @RequestParam("userNum") Long userNum,
                                 @RequestBody FastReservationResponseDto fastReservationResponseDto){
        fastReservationResponseDto.setReservationId(reservationId);
        fastReservationResponseDto.setUserNum(userNum);
        return apiFastReservationService.updateFastReservation(fastReservationResponseDto);
    }

    // 예약 취소(삭제)
    @DeleteMapping("/{reservationId}")
    public int deleteReservation(@PathVariable("reservationId") Long reservationId, @RequestParam("userNum") Long userNum){
        return apiFastReservationService.deleteFastReservation(reservationId, userNum);
    }
}
