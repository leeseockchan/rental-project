package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.ReviewDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserReviewMapper {

    // 리뷰 작성
    @Insert("INSERT INTO review (user_num, reservation_id, car_condition_satisfaction_rating, " +
            "reservation_process_satisfaction_rating, price_satisfaction_rating, " +
            "review_content, review_created_at) VALUES (#{userNum}, #{reservationId}, " +
            "#{carConditionSatisfactionRating}, #{reservationProcessSatisfactionRating}, " +
            "#{priceSatisfactionRating}, #{reviewContent}, NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "reviewId")
    int insertReview(ReviewDTO review);

    // 리뷰 수정
    @Update("UPDATE review SET car_condition_satisfaction_rating = #{carConditionSatisfactionRating}, " +
            "reservation_process_satisfaction_rating = #{reservationProcessSatisfactionRating}, " +
            "price_satisfaction_rating = #{priceSatisfactionRating}, " +
            "review_content = #{reviewContent}, review_created_at = NOW() " +
            "WHERE review_id = #{reviewId}")
    int updateReview(ReviewDTO review);

    // 리뷰 삭제
    @Delete("DELETE FROM review WHERE review_id = #{reviewId}")
    int deleteReview(@Param("reviewId") Long reviewId);

    // 특정 리뷰 조회
    @Select("SELECT * FROM review WHERE review_id = #{reviewId}")
    ReviewDTO findByReviewId(@Param("reviewId") Long reviewId);

    // 특정 유저의 페이징된 리뷰 목록 조회
    @Select("SELECT * FROM review WHERE user_num = #{userNum} " +
            "ORDER BY review_created_at DESC " +
            "LIMIT #{size} OFFSET #{offset}")
    List<ReviewDTO> findUserReviews(@Param("userNum") int userNum,
                                    @Param("size") int size,
                                    @Param("offset") int offset);

    // 특정 유저의 전체 리뷰 개수 조회
    @Select("SELECT COUNT(*) FROM review WHERE user_num = #{userNum}")
    int countUserReviews(@Param("userNum") int userNum);
}
