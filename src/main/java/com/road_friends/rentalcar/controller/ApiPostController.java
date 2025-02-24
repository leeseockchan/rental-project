package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.PostDto;
import com.road_friends.rentalcar.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class ApiPostController {
  private final PostService postService;

  @GetMapping
  public ResponseEntity<List<PostDto>> getAllPosts() {
    return ResponseEntity.ok(postService.getAllPosts());
  }

  @GetMapping("/{id}")
  public ResponseEntity<PostDto> getPost(@PathVariable Long id) {
    return ResponseEntity.ok(postService.getPost(id));
  }
}
