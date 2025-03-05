package com.road_friends.rentalcar.admin.car.mapper;

import com.road_friends.rentalcar.admin.car.dto.AdminCarDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminCarMapper {
    List<String> getDistrictsByProvince(@Param("province") String province);
    List<AdminCarDto> findByDistrict(@Param("district") String district);
    List<AdminCarDto> findAllCar();
    AdminCarDto findByCarId(int carId);
    void insertCar(AdminCarDto adminCarDto);
    /**
     * 모델명을 기반으로 modelId 조회
     */
    @Select("SELECT model_id FROM model WHERE model_name = #{modelName}")
    Integer findModelIdByName(@Param("modelName") String modelName);
    void modifyCar(AdminCarDto adminCarDto);
    void deleteCar(int carId);


    // 통계 그래프 데이터
    int countTotalVehicles();
    int countRentedVehicles();
    int countRepairVehicles();

    @Select("SELECT * FROM car")
    List<AdminCarDto> findAllCars();

    // 차량 등급별 개수 조회
    List<Map<String, Object>> getCarGradeCount();
    // 차량 보유 순위 조회
    List<Map<String, Object>> getCarRanking();
    // 제조사별 차량 개수 조회
    List<Map<String, Object>> getCarBrandCount();
}
