package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.APIReservationDto;
import com.road_friends.rentalcar.service.APIReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/short/reservations")
@CrossOrigin(origins = "http://localhost:3000")
public class APIReservationController {

    private  final APIReservationService apiReservationService;

    public APIReservationController(APIReservationService apiReservationService){
        this.apiReservationService = apiReservationService;
    }

    // 로그인한 사용자의 모든 예약 조회
    @GetMapping
    public List<APIReservationDto> getUserReservations(@RequestParam("userNum") Long userNum){
        return apiReservationService.getUserReservations(userNum);
    }

    // 특정 예약 상세 조회
    @GetMapping("/{reservationId}")
    public APIReservationDto getReservationDetail(@PathVariable("reservationId") int reservationSId){
        return apiReservationService.getReservationDetail(reservationSId);
    }

    // 이용 시작 (1 → 2)
    @PutMapping("/{reservationId}/start")
    public ResponseEntity<String> startRental(@PathVariable int reservationId) {
        boolean isUpdated = apiReservationService.updateRentalState(reservationId, 1, 2);

        if (isUpdated) {
            return ResponseEntity.ok("이용이 시작되었습니다. (차량 상태 변경 완료)");
        } else {
            return ResponseEntity.badRequest().body("이용 시작 실패 (예약 상태 또는 차량 상태 변경 실패)");
        }
    }

    // 이용 완료 (2 → 4)
    @PutMapping("/{reservationId}/complete")
    public ResponseEntity<String> completeRental(@PathVariable int reservationId) {
        boolean isUpdated = apiReservationService.completeRental(reservationId);
        return isUpdated ? ResponseEntity.ok("이용이 완료되었습니다.") : ResponseEntity.badRequest().body("이용 완료 실패");
    }

    // 예약취소 관리
    @PutMapping("/{reservationId}/cancel")
    public ResponseEntity<String> cancelRental(@PathVariable int reservationId) {
        boolean isUpdated = apiReservationService.cancelRental(reservationId);
        return isUpdated ? ResponseEntity.ok("예약이 취소되었습니다.") : ResponseEntity.badRequest().body("예약 취소 실패");
    }



    // 예약 정보 수정
    @PutMapping("/{reservationId}")
    public int updateReservation(@PathVariable("reservationId") int reservationSId, @RequestParam("userNum") Long userNum, @RequestBody APIReservationDto apiReservationDto){
        apiReservationDto.setReservationSId(reservationSId);
        apiReservationDto.setUserNum(userNum);
        return apiReservationService.updateReservation(apiReservationDto);
    }

    // 예약 취소(삭제)
    @DeleteMapping("/{reservationId}")
    public int deleteReservation(@PathVariable("reservationId") int reservationSId, @RequestParam("userNum") Long userNum){
        return apiReservationService.deleteReservation(reservationSId,userNum);
    }



}
