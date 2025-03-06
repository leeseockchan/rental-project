package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.PageDto;
import com.road_friends.rentalcar.dto.ReviewDTO;
import com.road_friends.rentalcar.dto.UserDTO;
import com.road_friends.rentalcar.mapper.AdminReviewMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminReviewService {

    private final AdminReviewMapper adminReviewMapper;

    public AdminReviewService(AdminReviewMapper adminReviewMapper) {
        this.adminReviewMapper = adminReviewMapper;
    }

    public PageDto<ReviewDTO> getAllReviews(int page, int size) {
        int offset = (page - 1) * size;
        List<ReviewDTO> reviews = adminReviewMapper.getAllReviews(offset, size);
        int totalCount = adminReviewMapper.getTotalReviewCount();
        return new PageDto<>(page, size, totalCount, reviews);
    }

    public ReviewDTO getReviewById(Long id) {
        return adminReviewMapper.getReviewById(id);
    }

    public UserDTO getUserById(Long userNum) {
        return adminReviewMapper.getUserById(userNum);
    }

    public void deleteReview(Long id) {
        adminReviewMapper.deleteReview(id);
    }
}