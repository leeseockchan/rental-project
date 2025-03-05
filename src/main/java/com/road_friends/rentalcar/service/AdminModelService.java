package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.AdminModelDto;
import com.road_friends.rentalcar.mapper.AdminModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminModelService {
    @Autowired
    private AdminModelMapper adminModelMapper;

    //모든 차량 조회
    public List<AdminModelDto> getAllmodels() {
        return adminModelMapper.findAll();
    }

    // 차량 ID로 조회
    public AdminModelDto getModelById(String modelId) {
        return adminModelMapper.findById(modelId);
    }

    // 차량 추가
    public void addModel(AdminModelDto adminModelDto) {
        adminModelMapper.insert(adminModelDto);
    }

    // 차량 수정
    public void modifyModel(AdminModelDto adminModelDto) {
        adminModelMapper.modify(adminModelDto);
    }

    // 차량 삭제
    public void deleteModel(String modelId) {
        adminModelMapper.delete(modelId);
    }
}
