package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.CarDto;
import com.road_friends.rentalcar.mapper.CarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarService {
    @Autowired
    private CarMapper carMapper;

    //    차량 관리 목록
    public List<CarDto> findAllCar() {
        return carMapper.findAllCar();
    }

    //    차량 관리 상세 보기
    public CarDto findByCarId(int carId) {
        return carMapper.findByCarId(carId);
    }

    //      차량 관리 추가
    public void insertCar(CarDto carDto) {
        carMapper.insertCar(carDto);
    }

    //    차량 관리 수정
    public void modifyCarStatus(CarDto carDto) {
        carMapper.modifyCar(carDto);
    }

    //    차량 상태 삭제
    public void deleteCarStatus(int carId) {
        carMapper.deleteCar(carId);
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
        return List.of("서울 특별시", "경기도", "충청북도", "충청남도",
                "경상북도", "경상남도", "전라북도", "전라남도", "제주도");
    }
    // 도/시별 행정구역을 Map으로 저장
    public Map<String, List<String>> parkingDistrictMap() {
        Map<String, List<String>> provinceToDistrictMap = new HashMap<>();

        // 도/시별 행정구역 매핑
        provinceToDistrictMap.put("서울 특별시", List.of("강남구", "강북구", "강서구", "양천구", "서초구", "광진구"));
        provinceToDistrictMap.put("경기도", List.of("용인시", "안양시", "부천시", "고양시", "성남시"));
        provinceToDistrictMap.put("충청북도", List.of("청주시", "충주시", "제천시", "음성군"));
        provinceToDistrictMap.put("충청남도", List.of("천안시", "논산시", "서산시", "당진시"));
        provinceToDistrictMap.put("경상북도", List.of("포항시", "경주시", "김천시", "구미시"));
        provinceToDistrictMap.put("경상남도", List.of("창원시", "김해시", "진주시", "마산시"));
        provinceToDistrictMap.put("전라북도", List.of("전주시", "익산시", "군산시", "정읍시"));
        provinceToDistrictMap.put("전라남도", List.of("목포시", "여수시", "순천시", "광양시"));
        provinceToDistrictMap.put("제주도", List.of("제주시", "서귀포시"));

        return provinceToDistrictMap;
    }

    public List<String> getDistrictsByProvince(String province) {
        Map<String, List<String>> provinceToDistrictMap = parkingDistrictMap();
        return provinceToDistrictMap.getOrDefault(province, new ArrayList<>());
    }


}
