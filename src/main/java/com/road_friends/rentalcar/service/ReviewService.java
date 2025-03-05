package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.ReviewDto;
import com.road_friends.rentalcar.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewMapper reviewMapper;

    public List<ReviewDto.ReviewCountByDate> getReviewCountByDate() {
        return reviewMapper.getReviewCountByDate().stream()
                .map(map -> new ReviewDto.ReviewCountByDate(
                        String.valueOf(map.get("review_date")),
                        ((Number) map.get("review_count")).intValue()
                ))
                .collect(Collectors.toList());
    }

    public ReviewDto.AverageSatisfaction getAverageSatisfaction() {
        Map<String, Object> result = reviewMapper.getAverageSatisfaction();
        return new ReviewDto.AverageSatisfaction(
                ((Number) result.getOrDefault("avg_car_condition", 0)).doubleValue(),
                ((Number) result.getOrDefault("avg_reservation_process", 0)).doubleValue(),
                ((Number) result.getOrDefault("avg_price", 0)).doubleValue()
        );
    }

    public List<ReviewDto.ReviewStatsByGenderAge> getReviewStatsByGenderAge() {
        return reviewMapper.getReviewStatsByGenderAge().stream()
                .map(map -> new ReviewDto.ReviewStatsByGenderAge(
                        String.valueOf(map.get("user_gender")),
                        String.valueOf(map.get("age_group")),
                        ((Number) map.get("review_count")).intValue(),
                        ((Number) map.getOrDefault("avg_car_condition", 0)).doubleValue(),
                        ((Number) map.getOrDefault("avg_reservation_process", 0)).doubleValue(),
                        ((Number) map.getOrDefault("avg_price", 0)).doubleValue()
                ))
                .collect(Collectors.toList());
    }

    public List<ReviewDto.ReviewStatsByCarModel> getReviewStatsByCarModel() {
        return reviewMapper.getReviewStatsByCarModel().stream()
                .map(map -> new ReviewDto.ReviewStatsByCarModel(
                        String.valueOf(map.get("model_name")),
                        ((Number) map.getOrDefault("avg_car_condition", 0)).doubleValue(),
                        ((Number) map.getOrDefault("avg_reservation_process", 0)).doubleValue(),
                        ((Number) map.getOrDefault("avg_price", 0)).doubleValue(),
                        ((Number) map.get("review_count")).intValue()
                ))
                .collect(Collectors.toList());
    }

    public List<ReviewDto.ReviewStatsByReservationType> getReviewStatsByReservationType() {
        return reviewMapper.getReviewStatsByReservationType().stream()
                .map(map -> new ReviewDto.ReviewStatsByReservationType(
                        String.valueOf(map.get("reservation_type")),
                        ((Number) map.getOrDefault("avg_car_condition", 0)).doubleValue(),
                        ((Number) map.getOrDefault("avg_reservation_process", 0)).doubleValue(),
                        ((Number) map.getOrDefault("avg_price", 0)).doubleValue(),
                        ((Number) map.get("review_count")).intValue()
                ))
                .collect(Collectors.toList());
    }
}
