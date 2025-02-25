package com.road_friends.rentalcar.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.road_friends.rentalcar.dto.CarDto;
import com.road_friends.rentalcar.dto.FastReservationDto;
import com.road_friends.rentalcar.dto.ModelDto;
import com.road_friends.rentalcar.dto.ParkingDto;
import com.road_friends.rentalcar.mapper.FastReservationMapper;
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
public class FastReservationService {

    @Autowired
    private FastReservationMapper fastReservationMapper;

    // 모든 차량 목록 조회
    public List<CarDto> getAllCars(){
        return fastReservationMapper.getAllCars();
    }

    // 차량 상세 정보 + 대여 가격 조회
    public Map<String, Object> getCarInfo(int carId, LocalDateTime startTime, LocalDateTime endTime) {
        CarDto car = fastReservationMapper.getCarById(carId);
        car.getModel().setModelAmountDay(fastReservationMapper.getAmountDay(car.getModel().getModelId()));
        car.getModel().setModelAmountHour(fastReservationMapper.getAmountHour(car.getModel().getModelId()));

        Long price = getTotalPrice(car, startTime, endTime);

        Map<String, Object> carDetail = new HashMap<>();
        carDetail.put("car", car);
        carDetail.put("totalPrice", price);

        return carDetail;
    }


    // 예약 목록
    public List<FastReservationDto> getReservations(){
        return fastReservationMapper.getAllReservations();
    };

    public FastReservationDto getReservationById(int id){
        return fastReservationMapper.getReservationById(id);
    }

    public void reserve(FastReservationDto fastReservationDto){

        CarDto car = getCarById(fastReservationDto.getCarId());
//        ModelDto model = getModelById(fastReservationDto.getCarId());
        fastReservationDto.setCarDto(car);
//        fastReservationDto.setModelDto(model);

        car.getModel().setModelAmountDay(fastReservationMapper.getAmountDay(car.getModel().getModelId()));
        car.getModel().setModelAmountHour(fastReservationMapper.getAmountHour(car.getModel().getModelId()));

        Long price = getTotalPrice(car, fastReservationDto.getRentalDatetime(), fastReservationDto.getReturnDatetime());

        fastReservationDto.setTotalPrice(price);

        fastReservationMapper.reserve(fastReservationDto);

    }


    public void deleteReservation(int reservationId) {
        fastReservationMapper.deleteReservation(reservationId);
    }

    public void updateReservation(FastReservationDto fastReservationDto) {
        fastReservationMapper.updateReservation(fastReservationDto);
    }


    // 차량 조회 + 가격 계산
    public Map<String,Object> getAvailableCars(String province, String district, LocalDateTime rentalDatetime, LocalDateTime returnDatetime,
                                              String modelCategory, String modelName) {

        List<CarDto> availableCars = fastReservationMapper.getAvailableCars(province, district, rentalDatetime, returnDatetime, modelCategory, modelName);

        // 여러 개의 차량 정보를 담을 리스트
        List<Map<String, Object>> carList = new ArrayList<>();

        for (CarDto car : availableCars) {
            Long price = getTotalPrice(car, rentalDatetime, returnDatetime);

            // 각 차량 정보를 Map 으로 저장
            Map<String, Object> carInfo = new HashMap<>();
            carInfo.put("car", car);
            carInfo.put("totalPrice", price);

            // 리스트에 추가
            carList.add(carInfo);
        }

        Map<String, Object> carListMap = new HashMap<>();
        carListMap.put("cars", carList);

        return carListMap;
    }



    // 반납 주차장 조회하기
    public List<ParkingDto> getParkingStation(LocalDateTime startTime, LocalDateTime endTime,int carId) {

        Long hoursBetween =  ChronoUnit.HOURS.between(startTime,endTime);

        List<ParkingDto> parkingList;

        // 4시간 예약일 경우 같은 도시 내의 주차장에서만 반납 가능
        if(hoursBetween==4){
            parkingList = fastReservationMapper.getParkingStationBelow4hours(carId);
        }
        else{
            parkingList = fastReservationMapper.getAllParkingStation(carId);
        }
        return parkingList;
    }


    // 가격 로직
    public Long getTotalPrice(CarDto carDto, LocalDateTime rentalDatetime, LocalDateTime returnDatetime) {
        if (rentalDatetime == null || returnDatetime == null) {
            throw new NullPointerException("대여 시작 시간과 종료 시간은 필수 입력 값");
        }

        Long totalPrice = 0L;
        Long hoursBetween = ChronoUnit.HOURS.between(rentalDatetime, returnDatetime);
        Long daysBetween = ChronoUnit.DAYS.between(rentalDatetime, returnDatetime);

        if (hoursBetween < 4) {
            throw new IllegalArgumentException("최소 4시간 이상 예약 가능");
        }
        if (daysBetween > 14) {
            throw new IllegalArgumentException("최대 14일까지 예약 가능");
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
        return fastReservationMapper.getCarById(carId);
    }

    public ModelDto getModelById(int modelId) {
        return fastReservationMapper.getModelById(modelId);
    }
}
