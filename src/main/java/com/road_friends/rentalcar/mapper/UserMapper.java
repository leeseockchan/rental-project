package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.DataPoint;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    // ✅ 전체 회원 수 조회
    @Select("SELECT COUNT(*) FROM `user`")
    int getTotalUsers();

    // ✅ 성별 통계 조회
    @Select("SELECT user_gender AS label, COUNT(*) AS count FROM `user` GROUP BY user_gender")
    List<DataPoint> getGenderStats();

    // 🚀 연령대별 회원 수 조회
    @Select("SELECT " +
            "CASE " +
            "WHEN YEAR(CURDATE()) - YEAR(user_birth) BETWEEN 20 AND 29 THEN '20대' " +
            "WHEN YEAR(CURDATE()) - YEAR(user_birth) BETWEEN 30 AND 39 THEN '30대' " +
            "WHEN YEAR(CURDATE()) - YEAR(user_birth) BETWEEN 40 AND 49 THEN '40대' " +
            "WHEN YEAR(CURDATE()) - YEAR(user_birth) BETWEEN 50 AND 59 THEN '50대' " +
            "WHEN YEAR(CURDATE()) - YEAR(user_birth) >= 60 THEN '60대 이상' " +
            "ELSE '기타' END AS label, " +
            "COUNT(*) AS count " +
            "FROM `user` " +
            "GROUP BY label")
    List<Map<String, Object>> getUserAgeGroups();
}
