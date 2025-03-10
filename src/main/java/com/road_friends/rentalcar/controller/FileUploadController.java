package com.road_friends.rentalcar.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
@CrossOrigin(origins = "http://localhost:3000")
public class FileUploadController {
  private final String uploadDir = "E:/images/notice/"; // 변경된 경로

  @PostMapping("/image")
  public ResponseEntity<Map<String, String>> uploadImage(@RequestParam("file") MultipartFile file) {
    if (file.isEmpty()) {
      return ResponseEntity.badRequest().body(Map.of("error", "파일이 없습니다."));
    }
    try {
      // 저장 폴더가 없으면 생성
      File dir = new File(uploadDir);
      if (!dir.exists()) {
        dir.mkdirs();
      }

      // 파일 저장
      String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
      Path path = Paths.get(uploadDir + fileName);
      Files.write(path, file.getBytes());

      // 이미지 URL 설정
      String imageUrl = "/images/notice/" + fileName; // 클라이언트가 접근할 URL
      return ResponseEntity.ok(Map.of("url", imageUrl));
    } catch (IOException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body(Map.of("error", "파일 저장 실패"));
    }
  }
}

