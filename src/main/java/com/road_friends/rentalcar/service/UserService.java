package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.DataPoint;
import com.road_friends.rentalcar.dto.UserStatsDto;
import com.road_friends.rentalcar.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public UserStatsDto getUserStats() {
        UserStatsDto stats = new UserStatsDto();

        // 👥 전체 회원 수
        stats.setTotalUsers(userMapper.getTotalUsers());

        // 🚻 성별 통계
        stats.setGenderStats(userMapper.getGenderStats());

        // 📌 연령대별 데이터 변환
        List<Map<String, Object>> ageData = userMapper.getUserAgeGroups();
        Map<String, Integer> ageGroups = ageData.stream()
                .collect(Collectors.toMap(
                        entry -> (String) entry.get("label"),
                        entry -> ((Long) entry.get("count")).intValue()
                ));
        stats.setAgeGroups(ageGroups);

        return stats;
    }

    public Map<String, Object> getUserStatsAsMap() {
        UserStatsDto stats = getUserStats();
        Map<String, Object> response = new HashMap<>();
        response.put("totalUsers", stats.getTotalUsers());
        response.put("genderStats", stats.getGenderStats());
        response.put("ageGroups", stats.getAgeGroups());
        return response;
    }
}
