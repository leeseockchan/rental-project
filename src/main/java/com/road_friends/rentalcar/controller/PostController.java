package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.PageDto;
import com.road_friends.rentalcar.dto.PostDto;
import com.road_friends.rentalcar.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/posts")
@RequiredArgsConstructor
public class PostController {
  private final PostService postService;

  @GetMapping("/list")
  public String list(@RequestParam(name = "page", defaultValue = "1") int page,
                     @RequestParam(name = "size", defaultValue = "10") int size,
                     @RequestParam(name = "title", required = false) String title,
                     Model model) {
    PageDto pageDto;

    if (title != null && !title.isEmpty()) {
      // 제목 검색 적용
      pageDto = postService.getPostsByTitle(page, size, title);
    } else {
      // 기존 전체 목록 조회
      pageDto = postService.getAllPosts(page, size);
    }

    model.addAttribute("pageDto", pageDto);
    model.addAttribute("title", title); // 검색어 유지
    return "notice/notice-list";
  }

  @GetMapping("/view/{id}")
  public String viewPost(@PathVariable Long id, Model model) {
    PostDto post = postService.getPost(id);
    model.addAttribute("post", post);
    return "notice/notice-detail";
  }

  @GetMapping("/write")
  public String writeForm(Model model) {
    model.addAttribute("post", new PostDto());
    return "notice/notice-create";
  }

  @PostMapping("/write")
  public String savePost(@ModelAttribute PostDto postDto) {
    postService.savePost(postDto);
    return "redirect:/admin/posts/list";
  }

  @GetMapping("/edit/{id}")
  public String editForm(@PathVariable Long id, Model model) {
    model.addAttribute("post", postService.getPost(id));
    return "notice/notice-update";
  }

  @PostMapping("/edit")
  public String updatePost(@ModelAttribute PostDto postDto) {
    postService.updatePost(postDto);
    return "redirect:/admin/posts/list";
  }

  @GetMapping("/delete/{id}")
  public String deletePost(@PathVariable Long id) {
    postService.deletePost(id);
    return "redirect:/admin/posts/list";
  }
}
