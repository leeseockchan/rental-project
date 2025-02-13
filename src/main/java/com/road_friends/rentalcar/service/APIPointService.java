package com.road_friends.rentalcar.service;


import com.road_friends.rentalcar.dto.APIPointDto;
import com.road_friends.rentalcar.mapper.APIPointMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class APIPointService {

    private final APIPointMapper apiPointMapper;

    public APIPointService(APIPointMapper apiPointMapper){
        this.apiPointMapper = apiPointMapper;
    }

    // 총 포인트 조회
    public int getTotalPoints(Long userNum){
        return apiPointMapper.getTotalPoints(userNum);
    }

    // 포인트 적립 내역 조회
    public List<APIPointDto> getPointHistory(Long userNum){
        return apiPointMapper.getPointHistory(userNum);
    }
}
