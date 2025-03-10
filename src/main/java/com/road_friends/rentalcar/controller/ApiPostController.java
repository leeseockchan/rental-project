package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.PageDto;
import com.road_friends.rentalcar.dto.PostDto;
import com.road_friends.rentalcar.service.ApiPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ApiPostController {
  private final ApiPostService apiPostService;

  @GetMapping
  public PageDto<PostDto> getAllPosts(@RequestParam(defaultValue = "1") int page,
                                      @RequestParam(defaultValue = "10") int size) {
    return apiPostService.getAllPosts(page, size);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PostDto> getPost(@PathVariable Long id) {
    return ResponseEntity.ok(apiPostService.getPost(id));
  }
}
