package com.road_friends.rentalcar.admin.car.controller;

import com.road_friends.rentalcar.admin.car.dto.AdminModelDto;
import com.road_friends.rentalcar.admin.car.service.AdminModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/admin/models")
public class AdminModelController {

    @Autowired
    private AdminModelService adminModelService;

    // 모든 차량정보 목록 조회
    @GetMapping
    public String showAllmodel(Model model) {
        List<AdminModelDto> models = adminModelService.getAllmodels();
        model.addAttribute("models", models);
        return "model_page/list";
    }

    // 차량정보 추가
    @GetMapping("/add")
    public String addModel(Model model){
        model.addAttribute("modelDto", new AdminModelDto());
        return "model_page/add";
    }
    @PostMapping("/add")
       public String add(@ModelAttribute AdminModelDto adminModelDto){
        adminModelService.addModel(adminModelDto);
        return "redirect:/api/admin/models";
    }

    //     차량정보 수정
    @GetMapping("/{modelId}/modify")
    public String modelModify(@PathVariable String modelId, Model model) {
        AdminModelDto adminModelDto = adminModelService.getModelById(modelId);
        model.addAttribute("modify", adminModelDto);
        return "model_page/modify";
    }
    @PutMapping("/{modelId}/modify")
    public String modifyModel(@PathVariable String modelId,
                              @ModelAttribute AdminModelDto adminModelDto) {
        adminModelDto.setModelId(modelId);
        adminModelService.modifyModel(adminModelDto);
        return "redirect:/api/admin/models";
    }

    //  차량정보 삭제
    @DeleteMapping("/{modelId}")
    public String deleteModel(@PathVariable String modelId){
        adminModelService.deleteModel(modelId);
        return "redirect:/api/admin/models";
    }

}


