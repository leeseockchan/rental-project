package com.road_friends.rentalcar.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FastReservationDto {

    @JsonProperty("reservation_id")
    private int reservationId;

    // 차량 id
    @JsonProperty("car_id")
    private int carId;

    // 사용자 번호
    @JsonProperty("user_num")
    private Long userNum;

    // 대여위치
    @JsonProperty("rental_location")
    private int rentalLocation;

    // 대여일시
    @JsonProperty("rental_datetime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rentalDatetime;

    // 반납위치
    @JsonProperty("return_location")
    private int returnLocation;

    // 반납일시
    @JsonProperty("return_datetime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime returnDatetime;

    @JsonProperty("fast_reservation_create_at")
    private Timestamp reservationTime;

    private CarDto carDto;
//    private ModelDto modelDto;
    private List<ParkingDto> parkingList;

    private Long totalPrice;

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = getTotalPrice();
    }

//    public Long getTotalPrice() {
//
//        if(rentalDatetime ==null || returnDatetime==null){
//            throw new NullPointerException("대여 시작 시간과 종료 시작은 필수 입력 값");
//        }
//
//        Long totalPrice = 0L;
//
//        Long hoursBetween =  ChronoUnit.HOURS.between(rentalDatetime,returnDatetime);
//        Long daysBetween = ChronoUnit.DAYS.between(rentalDatetime,returnDatetime);
//
//
//        if(hoursBetween <4){
//            throw new IllegalArgumentException("최소 4시간 이상 예약 가능");
//        }
//        if(daysBetween > 14){
//            throw new IllegalArgumentException("최대 14일까지 예약 가능");
//        }
//
//        int hourPrice = carDto.getModel().getModelAmountHour();
//        int dayPrice = carDto.getModel().getModelAmountDay();
//
//        if( hoursBetween<24 ){
//            // 4시간~하루 미만 예약일 때
//            totalPrice = hourPrice * hoursBetween;
//        }
//        else{
//            // 하루 이상 예약일 때
//            totalPrice = dayPrice * daysBetween + hourPrice * (hoursBetween%24);
//        }
//
//        if(carDto.getCarGrade().equalsIgnoreCase("Premium")){
//            totalPrice = (long) (totalPrice *1.2);
//        }
//
//        System.out.println("총 금액: "+totalPrice);
//        return totalPrice;
//    }
}
