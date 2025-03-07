package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.ReviewDTO;
import com.road_friends.rentalcar.dto.UserDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminReviewMapper {

    List<ReviewDTO> getAllReviews(@Param("offset") int offset, @Param("size") int size);

    int getTotalReviewCount();

    ReviewDTO getReviewById(@Param("id") Long id);

    UserDTO getUserById(@Param("userNum") Long userNum);

    void deleteReview(@Param("id") Long id);

    // 통계
    int getTotalResponded();
    int getTotalReservations();
    int getFastReservations();
    int getShortReservations();
    int getFastResponded();
    int getShortResponded();
}