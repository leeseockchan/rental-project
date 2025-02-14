package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    public Map<String, Object> getUserStats() {
        int totalUsers = userMapper.getTotalUsers();
        List<Map<String, Object>> genderStats = userMapper.getGenderStats();
        List<LocalDate> birthDates = userMapper.getUserBirthDates();

        // 연령대 계산
        Map<String, Integer> ageGroups = new HashMap<>();
        int currentYear = LocalDate.now().getYear();

        for (LocalDate birth : birthDates) {
            if (birth != null) { // NULL 체크 추가
                int age = currentYear - birth.getYear();
                String ageGroup = (age / 10 * 10) + "대";
                ageGroups.put(ageGroup, ageGroups.getOrDefault(ageGroup, 0) + 1);
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("totalUsers", totalUsers);
        result.put("genderStats", genderStats);
        result.put("ageGroups", ageGroups);
        return result;
    }
}
