package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.CarDto;
import com.road_friends.rentalcar.mapper.CarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
                "투싼", "코나", "스타리아", "아이오닉5", "아이오닉6",  "g70",
                "g80", "g90","gv60", "gv70", "gv80", "모닝", "레이", "k3", "k5", "k8", "k9",
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
    public List<String> parkingProvinceList(){
        return List.of("서울 특별시", "경기도", "충청북도", "충청남도",
                "경상북도", "경상남도", "전라북도", "전라남도", "제주도");
    }

//    주차장 행정 지역 리스트
    public List<String> parkingDistrictList(){
        return List.of( "평택시", "중구", "부천시", "이천시", "포천시", "용인시", "용산구", "과천시", "양주시",
                "서초구", "광명시", "도봉구", "성남시", "서대문구", "두천시", "오산시", "하남시", "고양시",
                "파주시", "관악구", "송파구", "동대문구", "안양시", "군포시", "노원구", "중랑구", "강남구",
                "강북구", "강서구", "양천구", "구로구", "용인시", "동작구", "안산시", "영등포구", "광산구",
                "울주군", "대덕구", "사상구", "사하구", "금정구", "기장군", "동래구", "서구", "남구",
                "수영구", "해운대구", "울산시", "대구시", "중구", "남해군", "함양군", "청양군", "여수시",
                "순천시", "김해시", "장성군", "담양군", "영암군", "순천시", "천안시", "합천군");
    }

//    cn

}
