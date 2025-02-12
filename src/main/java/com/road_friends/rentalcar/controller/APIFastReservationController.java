package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.APIFastReservationDto;
import com.road_friends.rentalcar.mapper.APIFastReservationMapper;
import com.road_friends.rentalcar.mapper.APIReservationMapper;
import com.road_friends.rentalcar.service.APIFastReservationService;
import com.road_friends.rentalcar.service.APIReservationService;
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
    public List<APIFastReservationDto> getUserFastReservations(@RequestParam Long userNum){
        return  apiFastReservationService.getUserFastReservations(userNum);
    }

    // 특정 빠른 예약 상세 조회
    @GetMapping("/{id}")
    public APIFastReservationDto getFastReservationDetail(@PathVariable Long id){
        return apiFastReservationService.getFastReservationDetail(id);
    }

    // 예약 정보 수정
    @PutMapping("/{id}")
    public int updateFastReservation(@PathVariable Long id, @RequestParam Long userNum,
                                 @RequestBody APIFastReservationDto apiFastReservationDto){
        apiFastReservationDto.setReservationId(id);
        apiFastReservationDto.setUserNum(userNum);
        return apiFastReservationService.updateFastReservation(apiFastReservationDto);
    }

    // 예약 취소(삭제)
    @DeleteMapping("/{id}")
    public int deleteReservation(@PathVariable Long id, @RequestParam Long userNum){
        return apiFastReservationService.deleteFastReservation(id, userNum);
    }



}
