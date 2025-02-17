package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.PageDto;
import com.road_friends.rentalcar.dto.ReviewDTO;
import com.road_friends.rentalcar.mapper.UserReviewMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserReviewService {

    private final UserReviewMapper userReviewMapper;

    public UserReviewService(UserReviewMapper userReviewMapper) {
        this.userReviewMapper = userReviewMapper;
    }

    // 특정 유저의 페이징된 리뷰 목록 조회
    public PageDto<ReviewDTO> getUserReviews(int userNum, int page, int size) {
        int totalElements = userReviewMapper.countUserReviews(userNum);
        int offset = (page - 1) * size;

        List<ReviewDTO> reviews = userReviewMapper.findUserReviews(userNum, size, offset);

        return new PageDto<>(page, size, totalElements, reviews);
    }

    public ReviewDTO getReviewById(Long id) {
        return Optional.ofNullable(userReviewMapper.findByReviewId(id))
                .orElseThrow(() -> new RuntimeException("리뷰를 찾을 수 없습니다."));
    }
}
