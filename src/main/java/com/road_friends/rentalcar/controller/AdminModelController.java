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

import static org.springframework.web.servlet.function.ServerResponse.ok;

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
        return "car/model-list";
    }

    // 차량정보 추가
    @GetMapping("/create")
    public String addModel(Model model) {
        model.addAttribute("modelDto", new AdminModelDto());
        return "car/model-create";
    }

    @PostMapping("/create")
    public  ResponseEntity<Map<String, Object>> addModel(@ModelAttribute AdminModelDto adminModelDto,
                                                        @RequestParam(value = "imageFile", required = false) MultipartFile file) {
        try {
            // 이미지가 선택된 경우
            if (file != null && !file.isEmpty()) {
                String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                Path imagePath = Paths.get("E:/images/car", filename);
                Files.createDirectories(imagePath.getParent());
                file.transferTo(imagePath.toFile());

                // DB에 저장할 이미지 URL
                adminModelDto.setImageUrl("/images/car/" + filename);
            } else {
                // 이미지가 선택되지 않은 경우 기본 이미지 URL 사용
                adminModelDto.setImageUrl("/images/car/defaultModel.png");
            }

            // DB에 모델 정보 저장
            adminModelService.addModel(adminModelDto);

            // 성공 응답 반환
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("model", adminModelDto); // 새로 추가된 모델 정보 포함
            return ResponseEntity.ok().body(response);

        } catch (IOException e) {
            e.printStackTrace();
            Map<String, Object> errorResponse = Map.of("success", false, "message", "파일 업로드 실패");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }


    //     차량정보 수정
    @GetMapping("/{modelId}/modify")
    public String modelModify(@PathVariable String modelId, Model model) {
        AdminModelDto adminModelDto = adminModelService.getModelById(modelId);
        model.addAttribute("modify", adminModelDto);
        return "car/model-update";
    }

    @PostMapping("/{modelId}/modify")
    @ResponseBody  // JSON 응답을 보낼 때 필요
    public ResponseEntity<String> modifyModel(@PathVariable String modelId,
                                              @ModelAttribute AdminModelDto adminModelDto,
                                              @RequestParam(value = "imageFile") MultipartFile file) {
        try {
            // 기존 차량 모델 정보 가져오기
            AdminModelDto existingModel = adminModelService.getModelById(adminModelDto.getModelId());
            String oldImageUrl = existingModel != null ? existingModel.getImageUrl() : null;

            // 새 이미지 저장
            if (file != null && !file.isEmpty()) {
                String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                // 서버에서 이미지를 저장하는 위치
                Path imagePath = Paths.get("E:/images/car", filename);

                // 폴더 없으면 생성 (폴더 없을 시 오류 방지)
                Files.createDirectories(imagePath.getParent());
                file.transferTo(imagePath.toFile());

                // 새 이미지 DB에 업데이트
                adminModelDto.setImageUrl("/images/car/" + filename);
            } else {
                // 기존 이미지가 없으면 기본 이미지 경로 설정
                adminModelDto.setImageUrl("/images/car/defaultModel.png");
            }

            // DB 업데이트
            adminModelDto.setModelId(modelId);
            adminModelService.modifyModel(adminModelDto);

            // JSON 형식으로 성공 응답 반환
            return ResponseEntity.ok("{\"success\": true}");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"success\": false, \"message\": \"파일 업로드 실패\"}");
        }
    }



    //  차량정보 삭제
    @DeleteMapping("/{modelId}")
    public String deleteModel(@PathVariable String modelId) {
        adminModelService.deleteModel(modelId);
        return "redirect:/api/admin/models";
    }

}
