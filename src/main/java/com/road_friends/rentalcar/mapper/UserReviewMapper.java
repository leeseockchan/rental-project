package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.ReviewDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserReviewMapper {

    @Insert("INSERT INTO review (user_num, car_id, car_condition_satisfaction_rating, " +
            "reservation_process_satisfaction_rating, price_satisfaction_satisfaction_rating, " +
            "review_content, review_created_at) VALUES (#{userNum}, #{carId}, #{carConditionSatisfactionRating}, " +
            "#{reservationProcessSatisfactionRating}, #{priceSatisfactionSatisfactionRating}, " +
            "#{reviewContent}, NOW())")
    int insertReview(ReviewDTO review);

    @Update("UPDATE review SET car_condition_satisfaction_rating = #{carConditionSatisfactionRating}, " +
            "reservation_process_satisfaction_rating = #{reservationProcessSatisfactionRating}, " +
            "price_satisfaction_satisfaction_rating = #{priceSatisfactionSatisfactionRating}, " +
            "review_content = #{reviewContent} WHERE review_id = #{reviewId}")
    int updateReview(ReviewDTO review);

    @Delete("DELETE FROM review WHERE review_id = #{reviewId}")
    int deleteReview(@Param("reviewId") Long reviewId);

    @Select("SELECT * FROM review WHERE review_id = #{reviewId}")
    ReviewDTO findByReviewId(@Param("reviewId") Long reviewId);
}