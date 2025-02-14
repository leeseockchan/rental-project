package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.ModelDto;
import com.road_friends.rentalcar.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/models")
public class ModelController {

    private final ModelService modelService;

    @Autowired
    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    // 모든 차량 목록 조회
    @GetMapping
    public ResponseEntity<List<ModelDto>> getAllmodels() {
        List<ModelDto> models = modelService.getAllmodels();
        return new ResponseEntity<>(models, HttpStatus.OK);
    }

    // 특정 차량 조회
    @GetMapping("/{modelId}")
    public ResponseEntity<ModelDto> getmodelById(@PathVariable("modelId") String modelId) {
        ModelDto model = modelService.getmodelById(modelId);
        return model != null ? new ResponseEntity<>(model, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // 차량 추가
    @PostMapping
    public ResponseEntity<ModelDto> addmodel(@RequestBody ModelDto modelDto) {
        modelService.addmodel(modelDto);
        return new ResponseEntity<>(modelDto, HttpStatus.CREATED);
    }

    // 차량 수정
    @PutMapping("/{modelId}")
    public ResponseEntity<ModelDto> updatemodel(@PathVariable("modelId") String modelId, @RequestBody ModelDto modelDto) {
        modelDto.setModelId(modelId); // modelId를 request body에 포함된 modelDto의 modelId로 설정
        modelService.updatemodel(modelDto);
        return new ResponseEntity<>(modelDto, HttpStatus.OK);
    }

    // 차량 삭제
    @DeleteMapping("/{modelId}")
    public ResponseEntity<Void> deletemodel(@PathVariable("modelId") String modelId) {
        modelService.deletemodel(modelId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
