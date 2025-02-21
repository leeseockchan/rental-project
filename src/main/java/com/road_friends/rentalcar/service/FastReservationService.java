package com.road_friends.rentalcar.service;

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
import java.util.List;

@Service
@Transactional
public class FastReservationService {

    @Autowired
    private FastReservationMapper fastReservationMapper;

    // 모든 차량 목록 조회
    public List<CarDto> getAllCars(){
        return fastReservationMapper.getAllCars();
    }

    // 차량 상세 정보 조회
    public CarDto getCarById(int carId) {
        CarDto car =  fastReservationMapper.getCarById(carId);
        car.getModel().setModelAmountDay(fastReservationMapper.getAmountDay(car.getModel().getModelId()));
        car.getModel().setModelAmountHour(fastReservationMapper.getAmountHour(car.getModel().getModelId()));

        return car;
    }

    // model 조회
    public ModelDto getModelById(Integer modelId) {
        return fastReservationMapper.getModelById(modelId);
    }

    // 예약 목록
    public List<FastReservationDto> getReservations(){
        return fastReservationMapper.getAllReservations();
    };

    public FastReservationDto getReservationById(int id){
        return fastReservationMapper.getReservationById(id);
    }

    public void reserve(FastReservationDto fastReservationDto){


        fastReservationMapper.reserve(fastReservationDto);
    }

    public void deleteReservation(int reservationId) {
        fastReservationMapper.deleteReservation(reservationId);
    }

    public void updateReservation(FastReservationDto fastReservationDto) {
        fastReservationMapper.updateReservation(fastReservationDto);
    }


    // 차량 조회 + 가격 계산
    public List<CarDto> getAvailableCars(String province, String district, LocalDateTime rentalDatetime, LocalDateTime returnDatetime,
                                         String modelCategory, String modelName) {
        List<CarDto> availableCars = fastReservationMapper.getAvailableCars(province, district, rentalDatetime, returnDatetime,
                modelCategory,modelName);

        for (CarDto car : availableCars){
            Long price = getPrice(car.getCarId(), car.getCarGrade(), rentalDatetime, returnDatetime);
            car.setCalculatedPrice(price);
        }
        return availableCars;
    }


    // 가격 계산 로직
    public Long getPrice(int carId, String carGrade, LocalDateTime startTime, LocalDateTime endTime) {

        if(startTime ==null || endTime==null){
            throw new NullPointerException("대여 시작 시간과 종료 시작은 필수 입력 값");
        }

        Long totalPrice = 0L;

        Long hoursBetween =  ChronoUnit.HOURS.between(startTime,endTime);
        Long daysBetween = ChronoUnit.DAYS.between(startTime,endTime);


        if(hoursBetween <4){
            throw new IllegalArgumentException("최소 4시간 이상 예약 가능");
        }
        if(daysBetween > 14){
            throw new IllegalArgumentException("최대 14일까지 예약 가능");
        }

        if( hoursBetween<24 ){
            // 4시간~하루 미만 예약일 때
            int hourPrice = fastReservationMapper.getAmountHour(carId);
            totalPrice = hourPrice * hoursBetween;
            System.out.println("하루 가격:"+hourPrice);
        }
        else{
            // 하루 이상 예약일 때
            int dayPrice = fastReservationMapper.getAmountDay(carId);
            totalPrice = dayPrice * daysBetween;
            System.out.println("하루 가격:"+dayPrice);
        }


        System.out.println("총 금액: "+totalPrice);
        return totalPrice;
    }

    // 반납 주차장 조회하기
    public List<ParkingDto> getParkingStation(LocalDateTime startTime, LocalDateTime endTime,int carId) {

        Long hoursBetween =  ChronoUnit.HOURS.between(startTime,endTime);

        List<ParkingDto> parkingList = new ArrayList<>();

        // 4시간 예약일 경우 같은 도시 내의 주차장에서만 반납 가능
        if(hoursBetween==4){
            parkingList = fastReservationMapper.getParkingStationBelow4hours(carId);
        }
        else{
            parkingList = fastReservationMapper.getAllParkingStation(carId);
        }
        return parkingList;
    }


}
