package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.ModelDto;
import com.road_friends.rentalcar.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/admin/models")
public class ModelController {

    @Autowired
    ModelService modelService;

    // 모든 차량 목록 조회
    @GetMapping
    public String showAllmodel(Model model) {
        List<ModelDto> models = modelService.getAllmodels();
        model.addAttribute("models", models);
        return "model_page/list";
    }

    // 차량 추가
    @GetMapping("/add")
    public String addModel(Model model){
        model.addAttribute("modelDto", new ModelDto());
        return "model_page/add";
    }
    @PostMapping("/add")
       public String add(@ModelAttribute ModelDto modelDto){
        modelService.addModel(modelDto);
        return "redirect:/api/admin/models";
    }

    //     차량 수정
    @GetMapping("/{modelId}/modify")
    public String modelModify(@PathVariable String modelId, Model model) {
        ModelDto modelDto = modelService.getModelById(modelId);
        model.addAttribute("modify", modelDto);
        return "model_page/modify";
    }
    @PutMapping("/{modelId}/modify")
    public String modifyModel(@PathVariable String modelId,
                              @ModelAttribute ModelDto modelDto) {
        modelDto.setModelId(modelId);
        modelService.modifyModel(modelDto);
        return "redirect:/api/admin/models";
    }

    //  차량정보 삭제
    @DeleteMapping("/{modelId}")
    public String deleteModel(@PathVariable String modelId){
        modelService.deleteModel(modelId);
        return "redirect:/api/admin/models";
    }

}


