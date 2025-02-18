package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.APIReservationDto;
import com.road_friends.rentalcar.service.APIReservationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
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
