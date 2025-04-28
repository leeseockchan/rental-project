package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.AdminCarDto;
import com.road_friends.rentalcar.mapper.AdminCarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

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
        // 🔹 modelId를 먼저 조회하여 설정
        int modelId = adminCarMapper.findModelIdByName(adminCarDto.getModel().getModelName());
        adminCarDto.setModelId(modelId);

        // 🚨 carGrade 값에 따라 carOptions 자동 설정
        if ("premium".equalsIgnoreCase(adminCarDto.getCarGrade())) {
            adminCarDto.setCarOptions("네비게이션,하이패스,블랙박스,후방카메라,열선시트");
        } else if ("standard".equalsIgnoreCase(adminCarDto.getCarGrade())) {
            adminCarDto.setCarOptions("블랙박스,하이패스,열선시트");
        } else {
            adminCarDto.setCarOptions(""); // 기본값 (옵션 없음)
        }
        adminCarMapper.insertCar(adminCarDto);
    }

    /**
     * 모델명을 기반으로 modelId 조회
     */
    public int getModelIdByName(String modelName) {
        if (modelName == null || modelName.trim().isEmpty()) {
            throw new IllegalArgumentException("모델명이 비어 있습니다.");
        }
        Integer modelId = adminCarMapper.findModelIdByName(modelName);
        if (modelId == null) {
            throw new NoSuchElementException("해당 모델명이 존재하지 않습니다: " + modelName);
        }
        return modelId;
    }

    //    차량 관리 수정
    public void modifyCarStatus(AdminCarDto adminCarDto) {

        // 🚨 carGrade 값에 따라 carOptions 자동 설정
        if ("premium".equalsIgnoreCase(adminCarDto.getCarGrade())) {
            adminCarDto.setCarOptions("네비게이션,하이패스,블랙박스,후방카메라,열선시트");
        } else if ("standard".equalsIgnoreCase(adminCarDto.getCarGrade())) {
            adminCarDto.setCarOptions("블랙박스,하이패스,열선시트");
        } else {
            adminCarDto.setCarOptions(""); // 기본값 (옵션 없음)
        }
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

    // 통계 그래프 데이터

    // 차량 통계
    public Map<String, Integer> getVehicleStatistics() {
        List<AdminCarDto> cars = adminCarMapper.findAllCars(); // ✅ 모든 차량 가져오기

        int total = cars.size(); // 전체 차량 수
        int rented = (int) cars.stream().filter(car -> car.getCarStatus() == 1).count(); // 대여 중
        int repair = (int) cars.stream().filter(car -> car.getCarStatus() == 2).count(); // 수리 중

        Map<String, Integer> stats = new HashMap<>();
        stats.put("total", total);
        stats.put("rented", rented);
        stats.put("repair", repair);

        return stats;
    }

    // 차량 등급별 개수 조회
    public List<Map<String, Object>> getCarGradeCount() {
        return adminCarMapper.getCarGradeCount();
    }
    // 차량 보유 순위 조회
    public List<Map<String, Object>> getCarRanking() {
        return adminCarMapper.getCarRanking();
    }
    // 제조사별 차량 개수 조회
    public List<Map<String, Object>> getCarBrandCount() {
        return adminCarMapper.getCarBrandCount();
    }


}