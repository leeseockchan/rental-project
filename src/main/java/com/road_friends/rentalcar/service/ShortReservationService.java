package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.*;
import com.road_friends.rentalcar.mapper.ShortReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ShortReservationService {

    @Autowired
    private ShortReservationMapper shortReservationMapper;

    // 모든 차량 목록 조회
    public List<CarDto> getAllCars(){
        return shortReservationMapper.getAllCars();
    }

    // 차량 상세 정보 + 대여 가격 조회
    public Map<String, Object> getCarInfo(int carId, LocalDateTime startTime, LocalDateTime endTime) {
        CarDto car = shortReservationMapper.getCarById(carId);
        car.getModel().setModelAmountDay(shortReservationMapper.getAmountDay(car.getModel().getModelId()));
        car.getModel().setModelAmountHour(shortReservationMapper.getAmountHour(car.getModel().getModelId()));

        Long price = getTotalPrice(car, startTime, endTime);

        Map<String, Object> carDetail = new HashMap<>();
        carDetail.put("car", car);
        carDetail.put("totalPrice", price);

        return carDetail;
    }


    // 예약 목록
    public List<ShortReservationDto> getReservations(){
        return shortReservationMapper.getAllReservations();
    };

    public ShortReservationDto getReservationById(int id){
        return shortReservationMapper.getReservationById(id);
    }

    // 예약하기
    @Transactional
    public void reserve(ShortReservationDto shortReservationDto){

        // reservation 테이블에 새로운 예약 생성
        ReservationDto reservationDto = new ReservationDto();
        shortReservationMapper.insertReservation(reservationDto);

        // 방금 생성된 reservationId 불러오기
        Integer reservationId = shortReservationMapper.getLastInsertId();
        shortReservationDto.setReservationSId(reservationId);

        CarDto car = getCarById(shortReservationDto.getCarId());
        shortReservationDto.setCarDto(car);

        shortReservationDto.setRentalStationStart(car.getRentalStation());

        car.getModel().setModelAmountDay(shortReservationMapper.getAmountDay(car.getModel().getModelId()));
        car.getModel().setModelAmountHour(shortReservationMapper.getAmountHour(car.getModel().getModelId()));

        Long price = getTotalPrice(car, shortReservationDto.getReservationSStartDate(), shortReservationDto.getReservationSEndDate());

        shortReservationDto.setTotalPrice(price);

        shortReservationMapper.reserve(shortReservationDto);

        // reservation 테이블의 short_reservation_id 업데이트
        shortReservationMapper.updateShortReservationId(reservationId, shortReservationDto.getReservationSId());
    }

    // 예약 삭제
    public void deleteReservation(int reservationId) {

        ShortReservationDto shortReservationDto = shortReservationMapper.getReservationById(reservationId);
        shortReservationMapper.updateCarStatusTo0(shortReservationDto.getCarId());
        shortReservationMapper.deleteReservation(reservationId);
    }

    public void updateReservation(ShortReservationDto shortReservationDto) {
        shortReservationMapper.updateReservation(shortReservationDto);
    }


    // 이용 가능한 차량 조회 + 가격 계산
    public Map<String,Object> getAvailableCars(String province, String district, LocalDateTime rentalDatetime, LocalDateTime returnDatetime,
                                               String modelCategory, String modelName, Integer endPrice) {


        List<CarDto> availableCars = shortReservationMapper.getAvailableCars(province, district, rentalDatetime, returnDatetime, modelCategory, modelName, endPrice);

        // 여러 개의 차량 정보를 담을 리스트
        List<Map<String, Object>> carList = new ArrayList<>();

        for (CarDto car : availableCars) {
            Long price = getTotalPrice(car, rentalDatetime, returnDatetime);

            if(endPrice == null || price<=endPrice){
                // 각 차량 정보를 Map 으로 저장
                Map<String, Object> carInfo = new HashMap<>();
                carInfo.put("car", car);
                carInfo.put("totalPrice", price);

                // 리스트에 추가
                carList.add(carInfo);
            }

        }

        Map<String, Object> carListMap = new HashMap<>();
        carListMap.put("cars", carList);

        return carListMap;
    }

    // 가격 로직
    public Long getTotalPrice(CarDto carDto, LocalDateTime rentalDatetime, LocalDateTime returnDatetime) {
        if (rentalDatetime == null || returnDatetime == null) {
            throw new NullPointerException("대여 시작 시간과 종료 시간은 필수 입력 값");
        }

        Long totalPrice = 0L;
        Long hoursBetween = ChronoUnit.HOURS.between(rentalDatetime, returnDatetime);
        Long daysBetween = ChronoUnit.DAYS.between(rentalDatetime, returnDatetime);

        if (daysBetween < 14) { // 최소 14일 이상
            throw new IllegalArgumentException("최소 14일 이상 예약 가능");
        }
        if (daysBetween > 120) { // 최대 120일 까지
            throw new IllegalArgumentException("최대 120일까지만 예약 가능");
        }

        int hourPrice = carDto.getModel().getModelAmountHour();
        int dayPrice = carDto.getModel().getModelAmountDay();

        if (hoursBetween < 24) {
            totalPrice = hourPrice * hoursBetween;
        } else {
            totalPrice = dayPrice * daysBetween + hourPrice * (hoursBetween % 24);
        }

        if (carDto.getCarGrade().equalsIgnoreCase("Premium")) {
            totalPrice = (long) (totalPrice * 1.2);
        }

        return totalPrice;
    }


    public CarDto getCarById(int carId) {
        return shortReservationMapper.getCarById(carId);
    }

    public ModelDto getModelById(int modelId) {
        return shortReservationMapper.getModelById(modelId);
    }

    // rental_state를 0에서 1로 업데이트
    public void updateRentalStateToConfirmed(int reservationId) {
        shortReservationMapper.updateRentalState(reservationId);
    }
}