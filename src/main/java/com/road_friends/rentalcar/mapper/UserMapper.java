package com.road_friends.rentalcar.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserMapper {

    // 전체 회원 수 조회
    @Select("SELECT COUNT(*) FROM `user`")
    int getTotalUsers();

    // 성별 통계 조회
    @Select("SELECT user_gender, COUNT(*) AS count FROM `user` GROUP BY user_gender")
    List<Map<String, Object>> getGenderStats();

    // 생년월일 목록 조회
    @Select("SELECT user_birth FROM `user`")
    List<LocalDate> getUserBirthDates();
}
