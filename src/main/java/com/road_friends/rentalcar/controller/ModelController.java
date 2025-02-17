package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.ModelDto;
import com.road_friends.rentalcar.service.ModelService;
import com.road_friends.rentalcar.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/models")
public class ModelController {

    @Autowired
    ModelService modelService;

    // 모든 차량 목록 조회
    @GetMapping
    public String showAllmodel(Model model){
        List<ModelDto> models = modelService.getAllmodels();
        model.addAttribute("models", models);
        return "model_page/list";
    }

    // 특정 차량 조회
    @GetMapping("/{modelId}")
    public String findByModel(@PathVariable("modelId") String modelId, Model model) {
            ModelDto modelDetail = modelService.getmodelById(modelId);
            model.addAttribute("model", modelDetail);
            return "model_page/detail";
    }
    // 차량 상세보기


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
