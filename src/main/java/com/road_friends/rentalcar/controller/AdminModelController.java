package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.AdminModelDto;
import com.road_friends.rentalcar.service.AdminModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/admin/models")
public class AdminModelController {

    private static final String IMAGE_DIR = "E:/images/car";

    @Autowired
    private AdminModelService adminModelService;

    // 모든 차량정보 목록 조회
    @GetMapping
    public String showAllModel(Model model) {
        List<AdminModelDto> models = adminModelService.getAllmodels();
        model.addAttribute("models", models);
        return "car/model-list";
    }

    // 차량정보 추가 페이지
    @GetMapping("/create")
    public String addModelForm(Model model) {
        model.addAttribute("modelDto", new AdminModelDto());
        return "car/model-create";
    }

    // 차량정보 추가
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> addModel(@ModelAttribute AdminModelDto adminModelDto,
                                                        @RequestParam(value = "imageFile") MultipartFile file) {
        Map<String, Object> response = new HashMap<>();
        try {
            String imageUrl = saveImage(file);
            adminModelDto.setImageUrl(imageUrl);

            adminModelService.addModel(adminModelDto);

            response.put("success", true);
            response.put("model", adminModelDto);
            return ResponseEntity.ok(response);

        } catch (IOException e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "파일 업로드 실패");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // 차량정보 수정 페이지
    @GetMapping("/{modelId}/modify")
    public String modelModify(@PathVariable String modelId, Model model) {
        AdminModelDto adminModelDto = adminModelService.getModelById(modelId);
        model.addAttribute("modify", adminModelDto);
        return "car/model-update";
    }

    // 차량정보 수정
    @PostMapping("/{modelId}/modify")
    @ResponseBody
    public ResponseEntity<String> modifyModel(@PathVariable String modelId,
                                              @ModelAttribute AdminModelDto adminModelDto,
                                              @RequestParam(value = "imageFile", required = false) MultipartFile file) {
        try {
            AdminModelDto existingModel = adminModelService.getModelById(modelId);
            String oldImageUrl = existingModel != null ? existingModel.getImageUrl() : null;

            // 새 파일이 업로드되었을 경우만 이미지 변경
            if (file != null && !file.isEmpty()) {
                // 이전 이미지 삭제 (기본 이미지 제외)
                deleteImage(oldImageUrl);

                // 새 이미지 저장
                String newImageUrl = saveImage(file);
                adminModelDto.setImageUrl(newImageUrl);
            } else {
                // 새 이미지가 없으면 기존 이미지 유지
                adminModelDto.setImageUrl(oldImageUrl);
            }

            adminModelDto.setModelId(modelId);
            adminModelService.modifyModel(adminModelDto);

            return ResponseEntity.ok("{\"success\": true}");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"success\": false, \"message\": \"파일 업로드 실패\"}");
        }
    }

    // 차량정보 삭제
    @DeleteMapping("/{modelId}")
    public String deleteModel(@PathVariable String modelId) {
        AdminModelDto existingModel = adminModelService.getModelById(modelId);
        if (existingModel != null) {
            deleteImage(existingModel.getImageUrl());
        }
        adminModelService.deleteModel(modelId);
        return "redirect:/admin/models";
    }

    // 이미지 저장 메서드
    private String saveImage(MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path imagePath = Paths.get(IMAGE_DIR, filename);

            Files.createDirectories(imagePath.getParent());

            try {
                file.transferTo(imagePath.toFile());
            } catch (IOException e) {
                Files.deleteIfExists(imagePath);
                throw e;
            }

            return "/images/car/" + filename;
        } else {
            return "/images/car/defaultModel.png";
        }
    }

    // 이미지 삭제 메서드
    private void deleteImage(String imageUrl) {
        try {
            if (imageUrl != null && !imageUrl.contains("defaultModel.png")) {
                Path path = Paths.get("E:/" + imageUrl.replaceFirst("/", ""));
                Files.deleteIfExists(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
