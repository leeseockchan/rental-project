package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.ReviewDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserReviewMapper {

    // 리뷰 작성
    @Insert("INSERT INTO review (user_id, car_name, rating, title, content, created_date) " +
            "VALUES (#{userId}, #{carName}, #{rating}, #{title}, #{content}, #{createdDate})")
    int insertReview(ReviewDTO review);

    // 리뷰 수정
    @Update("UPDATE review SET rating = #{rating}, title = #{title}, content = #{content} " + "WHERE id = #{id}")
    int updateReview(ReviewDTO review);

    // 리뷰 삭제
    @Delete("DELETE FROM review WHERE id = #{id} ")
    int deleteReview(@Param("id") Long id);

    // 특정 리뷰 조회
    @Select("SELECT * FROM review WHERE id = #{id}")
    ReviewDTO findByReviewId(@Param("id") Long id);

}
