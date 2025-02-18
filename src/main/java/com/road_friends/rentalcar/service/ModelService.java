package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.ModelDto;
import com.road_friends.rentalcar.mapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelService {
    @Autowired
    private ModelMapper modelMapper;

    //모든 차량 조회
    public List<ModelDto> getAllmodels() {
        return modelMapper.findAll();
    }

    // 차량 ID로 조회
    public ModelDto getModelById(String modelId) {
        return modelMapper.findById(modelId);
    }

    // 차량 추가
    public void addModel(ModelDto modelDto) {
        modelMapper.insert(modelDto);
    }

    // 차량 수정
    public void modifyModel(ModelDto modelDto) {
        modelMapper.modify(modelDto);
    }

    // 차량 삭제
    public void deleteModel(String modelId) {
        modelMapper.delete(modelId);
    }
}
