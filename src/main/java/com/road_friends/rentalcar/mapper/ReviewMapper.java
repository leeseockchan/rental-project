package com.road_friends.rentalcar.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReviewMapper {

//   날짜별 리뷰 개수 조회
    List<Map<String, Object>> getReviewCountByDate();

//    평균 만족도 점수 조회
    Map<String, Object> getAverageSatisfaction();

//    성별 및 연령대별 리뷰 통계 조회
    List<Map<String, Object>> getReviewStatsByGenderAge();

//    차량 모델별 리뷰 통계 조회
    List<Map<String, Object>> getReviewStatsByCarModel();

//    예약 유형별 만족도 차이 조회
    List<Map<String, Object>> getReviewStatsByReservationType();
}