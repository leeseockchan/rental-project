package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.AdminParkingDto;
import com.road_friends.rentalcar.mapper.AdminParkingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminParkingService {
    @Autowired
    private AdminParkingMapper adminParkingMapper;

    // 1. 모든 도/시(province) 목록 조회
    public List<String> getAllProvinces() {
        return adminParkingMapper.findAllProvinces();
    }
    // 3. 특정 행정구역의 주차장 목록 조회
    public List<AdminParkingDto> getParkingsByDistrict(String province, String district) {
        return adminParkingMapper.findParkingsByDistrict(province, district);
    }

    public List<String> getDistrictsByProvince(String province) {
        return adminParkingMapper.getDistrictsByProvince(province);
    }

    public List<AdminParkingDto> findByDistrict(String district) {
        return adminParkingMapper.findByDistrict(district);
    }

    //     전체 주차장 목록
    public List<AdminParkingDto> findAll() {
        return adminParkingMapper.findAll();
    }
    //      특정 주차장 조회
    public AdminParkingDto findByParking(int parkingId) {
        return adminParkingMapper.findByParking(parkingId);
    }

    //    주차장 추가하기
    public void addParking(AdminParkingDto adminParkingDto) {
        adminParkingMapper.addParking(adminParkingDto);
    }

    //    주차장 수정하기
    public void modifyParking(AdminParkingDto adminParkingDto) {
        adminParkingMapper.modifyParking(adminParkingDto);
    }

    //    주차장 삭제하기
    public void deleteParking(int parkingId) {
        adminParkingMapper.deleteParking(parkingId);
    }


    // 주차장 통계
    public Map<String, Integer> getParkingStatistics() {
        Map<String, Integer> stats = new HashMap<>();

        // 주차장 통계 (총 주차장, 수도권/비수도권)
        stats.put("total", adminParkingMapper.getTotalParkingCount());
        stats.put("capital", adminParkingMapper.getSeoulGyeonggiIncheonCount());
        stats.put("nonCapital", adminParkingMapper.getOtherRegionCount());

        return stats;
    }

    public List<Map<String, Object>> getParkingCountByRegion() {
        return adminParkingMapper.getParkingCountByRegion();
    }

    public List<AdminParkingDto> getTop5ParkingStats() {
        return adminParkingMapper.getTop5ParkingStats();
    }


}