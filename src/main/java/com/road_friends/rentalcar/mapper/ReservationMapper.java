package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.ReservationDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ReservationMapper {

    // 평균 렌트 시간
    List<ReservationDto> getAverageRentalDurations();

    // 가장 많이 대여한 지역 TOP 5
    List<ReservationDto> getTopRentalLocations();

    // 가장 많이 반납한 지역 TOP 5
    List<ReservationDto> getTopReturnLocations();

    // 가장 인기 있는 차량 TOP 5
    List<ReservationDto> getPopularCars();
}

