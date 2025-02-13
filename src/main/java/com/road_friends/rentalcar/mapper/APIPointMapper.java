package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.APIPointDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface APIPointMapper {

    // 총 포인트 조회
    int getTotalPoints(Long userNum);

    // 포인트 적립 내역 조회
    List<APIPointDto> getPointHistory(Long userNum);
}
