package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.APIPointDto;
import com.road_friends.rentalcar.service.APIPointService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/points")
public class APIPointController {

    private final APIPointService apiPointService;

    public APIPointController(APIPointService apiPointService){
        this.apiPointService = apiPointService;
    }

    // 총 포인트 조회
    @GetMapping("/total")
    public int getTotalPoints(@RequestParam("userNum") Long userNum){
        return apiPointService.getTotalPoints(userNum);
    }

    // 포인트 적립 내역 조회
    @GetMapping("/history")
    public List<APIPointDto> getPointHistory(@RequestParam("userNum") Long userNum){
        return apiPointService.getPointHistory(userNum);
    }
}
