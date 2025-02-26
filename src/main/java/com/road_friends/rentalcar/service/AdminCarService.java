package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.AdminCarDto;
import com.road_friends.rentalcar.mapper.AdminCarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminCarService {
    @Autowired
    private AdminCarMapper adminCarMapper;

    public List<String> getDistrictsByProvince(String province) {
        return adminCarMapper.getDistrictsByProvince(province);
    }

    public List<AdminCarDto> findByDistrict(String district) {
        return adminCarMapper.findByDistrict(district);
    }

    //    차량 관리 상세 보기
    public AdminCarDto findByCarId(int carId) {
        return adminCarMapper.findByCarId(carId);
    }

    //      차량 관리 추가
    public void insertCar(AdminCarDto adminCarDto) {
        adminCarMapper.insertCar(adminCarDto);
    }

    //    차량 관리 수정
    public void modifyCarStatus(AdminCarDto adminCarDto) {
        adminCarMapper.modifyCar(adminCarDto);
    }

    //    차량 상태 삭제
    public void deleteCarStatus(int carId) {
        adminCarMapper.deleteCar(carId);
    }

    //    차량 제조사
    public List<String> carBrandList() {
        return List.of("현대", "제네시스", "기아", "KGM");
    }

    //    차량 이름
    public List<String> modelNameList() {
        return List.of("캐스퍼", "베뉴", "아반떼", "쏘나타", "그랜저", "팰리세이드", "싼타페",
                "투싼", "코나", "스타리아", "아이오닉5", "아이오닉6", "g70",
                "g80", "g90", "gv60", "gv70", "gv80", "모닝", "레이", "k3", "k5", "k8", "k9",
                "셀토스", "니로", "스포티지", "쏘렌토", "카니발", "EV3", "EV6", "Ev9",
                "티볼리", "코란도", "토레스", "액티언");
    }

    //    차량 연식
    public List<Integer> carYearList() {
        return List.of(2020, 2021, 2022, 2023, 2024, 2025);
    }

    //  차량 연료
    public List<String> carFuelList() {
        return List.of("가솔린", "경유", "전기");
    }

    //  차량 등급 리스트
    public List<String> carGradeList() {
        return List.of("Premium", "Standard");
    }

    //    주차장 도/시 리스트
    public List<String> parkingProvinceList() {
        return List.of("서울특별시", "인천광역시" ,"경기도", "충청남도", "충청북도",
                 "경상북도", "경상남도", "강원도", "전라북도", "전라남도", "제주도");
    }

}
