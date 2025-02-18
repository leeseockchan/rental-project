package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.ModelDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ModelMapper {
    List<ModelDto> findAll();
    ModelDto findById(String modelId);
    void insert(ModelDto modelDto);
    void modify(ModelDto modelDto);
    void delete(String modelId);
}
