package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.AdminCarDto;
import com.road_friends.rentalcar.dto.AdminParkingDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminParkingMapper {

    // 1. 모든 도/시(province) 목록 가져오기
    @Select("SELECT DISTINCT parking_province FROM parking ORDER BY parking_province")
    List<String> findAllProvinces();

    // 2. 특정 도/시의 행정구역(district) 목록 가져오기
    @Select("SELECT DISTINCT parking_district FROM parking WHERE parking_province = #{province} ORDER BY parking_district")
    List<String> findDistrictsByProvince(@Param("province") String province);

    // 3. 특정 행정구역에 속하는 주차장 목록 가져오기
    @Select("SELECT parking_id, parking_name, parking_address, parking_latitude, parking_longtitude, parking_province, parking_district " +
            "FROM parking WHERE parking_province = #{province} AND parking_district = #{district} ORDER BY parking_name")
    List<AdminParkingDto> findParkingsByDistrict(@Param("province") String province, @Param("district") String district);

    List<String> getDistrictsByProvince(@Param("province") String province);
    @Select("SELECT p.parking_id, p.parking_name, p.parking_address, p.parking_latitude, p.parking_longtitude, p.parking_province, p.parking_district, " +
            "COUNT(c.car_id) AS carCount " +
            "FROM parking p " +
            "LEFT JOIN car c ON p.parking_id = c.rental_station " +
            "WHERE p.parking_district = #{district} " +
            "GROUP BY p.parking_id")
    List<AdminParkingDto> findByDistrict(String district);
    //    주차장 목록 메소드
    List<AdminParkingDto> findAll();
    //    주차장 상세보기
    AdminParkingDto findByParking(int parkingId);
    // 해당 주차장에 있는 차량 목록 조회
    @Select("""
        SELECT 
            car.car_id,
            car.car_category,
            car.car_status,
            car.car_year,
            car.car_fuel,
            car.car_grade,
            model.model_brand,
            model.model_name,
            model.model_category,
            model.model_seate_num,
            model.model_transmission,
            model.model_amount_hour,
            model.model_amount_day
        FROM car
        JOIN model ON car.model_id = model.model_id
        WHERE car.rental_station = #{parkingId}
    """)
    List<AdminCarDto> findCarsByParking(int parkingId);
    //    주차장 추가
    void addParking(AdminParkingDto adminParkingDto);
    //    주차장 수정
    void modifyParking(AdminParkingDto adminParkingDto);
    //    주차장 삭제
    void deleteParking(int parkingId);

    // 주차장 통계
    @Select("SELECT COUNT(*) FROM parking")
    int getTotalParkingCount();
    @Select("SELECT COUNT(*) FROM parking WHERE parking_province IN ('서울특별시', '경기도', '인천광역시')")
    int getSeoulGyeonggiIncheonCount();
    @Select("SELECT COUNT(*) FROM parking WHERE parking_province NOT IN ('서울특별시', '경기도', '인천광역시')")
    int getOtherRegionCount();

    List<AdminParkingDto> getTop5ParkingStats();

    List<Map<String, Object>> getParkingCountByRegion();
}