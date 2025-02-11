package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.ModelDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ModelMapper {
    List<ModelDto> findAll();
    ModelDto findById(int modelId);
    void insert(ModelDto modelDto);
    void update(ModelDto modelDto);
    void delete(int carId);
}
