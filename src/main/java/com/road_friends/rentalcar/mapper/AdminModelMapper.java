package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.AdminModelDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface AdminModelMapper {
    List<AdminModelDto> findAll();
    AdminModelDto findById(String modelId);
    void insert(AdminModelDto adminModelDto);
    void modify(AdminModelDto adminModelDto);
    void delete(String modelId);
}
