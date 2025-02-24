package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.PostDto;
import com.road_friends.rentalcar.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
  private final PostService postService;

  @GetMapping("/list")
  public String list(Model model) {
    List<PostDto> posts = postService.getAllPosts();
    model.addAttribute("posts", posts);
    return "notice/list";
  }

  @GetMapping("/view/{id}")
  public String viewPost(@PathVariable Long id, Model model) {
    PostDto post = postService.getPost(id);
    model.addAttribute("post", post);
    return "notice/view";
  }

  @GetMapping("/write")
  public String writeForm(Model model) {
    model.addAttribute("post", new PostDto());
    return "notice/write";
  }

  @PostMapping("/write")
  public String savePost(@ModelAttribute PostDto postDto) {
    postService.savePost(postDto);
    return "redirect:/posts/list";
  }

  @GetMapping("/edit/{id}")
  public String editForm(@PathVariable Long id, Model model) {
    model.addAttribute("post", postService.getPost(id));
    return "notice/edit";
  }

  @PostMapping("/edit")
  public String updatePost(@ModelAttribute PostDto postDto) {
    postService.updatePost(postDto);
    return "redirect:/posts/list";
  }

  @GetMapping("/delete/{id}")
  public String deletePost(@PathVariable Long id) {
    postService.deletePost(id);
    return "redirect:/posts/list";
  }
}
