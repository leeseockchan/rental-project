package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.DataPoint;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    // ✅ 전체 회원 수 조회
    int getTotalUsers();

    // ✅ 성별 통계 조회
    List<DataPoint> getGenderStats();

    // 🚀 연령대별 회원 수 조회
    List<Map<String, Object>> getUserAgeGroups();
}
