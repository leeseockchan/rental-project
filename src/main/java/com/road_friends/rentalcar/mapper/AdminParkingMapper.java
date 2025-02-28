package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.AdminParkingDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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
    @Select("SELECT * FROM parking WHERE parking_district = #{district}")
    List<AdminParkingDto> findByDistrict(String district);
    //    주차장 목록 메소드
    List<AdminParkingDto> findAll();
    //    주차장 상세보기
    AdminParkingDto findByParking(int parkingId);
    //    주차장 추가
    void addParking(AdminParkingDto adminParkingDto);
    //    주차장 수정
    void modifyParking(AdminParkingDto adminParkingDto);
    //    주차장 삭제
    void deleteParking(int parkingId);


}