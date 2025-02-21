package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.DataPoint;
//import com.road_friends.rentalcar.dto.ShortReservationDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import javax.xml.crypto.Data;
import java.util.List;

@Mapper
public interface ReservationMapper {

    // 예약이 많은 시간대 TOP 5 (빠른 예약)
    List<DataPoint> getTopFastRentalHours();

    // 예약이 많은 시간대 TOP 5 (단기 예약)
//    List<DataPoint> getTopShortRentalLocations();


    // 가장 많이 대여된 지역 TOP 5 (빠른 예약)
    List<DataPoint> getTopFastRentalLocations();

    // 가장 많이 대여된 지역 TOP 5 (단기 예약)
    List<DataPoint> getTopShortRentalLocations();


    // 가장 많이 반납된 지역 TOP 5 (빠른 예약)
    List<DataPoint> getTopFastReturnLocations();

    // 가장 많이 반납된 지역 TOP 5 (단기 예약)
    List<DataPoint> getTopShortReturnLocations();


    // 가장 인기 있는 차량 TOP 5 (빠른 예약)
    List<DataPoint> getTopFastPopularCars();

    // 가장 인기 있는 차량 TOP 5 (단기 예약)
    List<DataPoint> getTopShortPopularCars();

    // 차량별 평균 렌트 시간 TOP 5 (빠른 예약)
    List<DataPoint> getTopFastCarRentalDuration();

    // 차량별 평균 렌트 시간 TOP 5 (단기 예약)
    List<DataPoint> getTopShortCarRentalDuration();

    // 지역별 평균 렌트 시간 TOP 5 (빠른 예약)
    List<DataPoint> getTopFastRegionRentalDuration();

    // 지역별 평균 렌트 시간 TOP 5 (단기 예약)
    List<DataPoint> getTopShortRegionRentalDuration();



    // 사용자별 평균 렌트 시간 TOP 5 (빠른 예약)
    List<DataPoint> getTopFastUserRentalDuration();
    // 사용자별 평균 렌트 시간 TOP 5 (단기 예약)

    List<DataPoint> getTopShortUserRentalDuration();

    // 가장 인기 있는 차량 TOP 5
    List<DataPoint> getTopPopularCars();

}
