package com.road_friends.rentalcar.dto;

import java.util.List;
import java.util.Map;

public class UserStatsDto {
    private int totalUsers;  // 총 회원 수
    private List<DataPoint> genderStats;  // 성별 통계
    private Map<String, Integer> ageGroups;  // 연령대별 통계

    // ✅ 기본 생성자 추가 (해결 방법)
    public UserStatsDto() {
    }

    public UserStatsDto(int totalUsers, List<DataPoint> genderStats, Map<String, Integer> ageGroups) {
        this.totalUsers = totalUsers;
        this.genderStats = genderStats;
        this.ageGroups = ageGroups;
    }

    public int getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(int totalUsers) {
        this.totalUsers = totalUsers;
    }

    public List<DataPoint> getGenderStats() {
        return genderStats;
    }

    public void setGenderStats(List<DataPoint> genderStats) {
        this.genderStats = genderStats;
    }

    public Map<String, Integer> getAgeGroups() {
        return ageGroups;
    }

    public void setAgeGroups(Map<String, Integer> ageGroups) {
        this.ageGroups = ageGroups;
    }
}
