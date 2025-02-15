package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.ReviewDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminReviewMapper {

    // 전체 리뷰 조회
    List<ReviewDTO> findAllReviews();

    // 특정 리뷰 조회
    ReviewDTO findByReviewId(@Param("id") Long id);

    // 리뷰 삭제
    int deleteReview(@Param("id") Long id);
}
