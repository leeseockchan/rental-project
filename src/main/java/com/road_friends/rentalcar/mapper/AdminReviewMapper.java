package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.ReviewDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminReviewMapper {

    // 페이징된 리뷰 목록 조회
    List<ReviewDTO> findAllReviews(@Param("size") int size, @Param("offset") int offset);

    // 전체 리뷰 개수 조회
    int countReviews();

    // 특정 리뷰 조회
    ReviewDTO findByReviewId(@Param("id") Long id);

    // 리뷰 삭제
    int deleteReview(@Param("id") Long id);
}
