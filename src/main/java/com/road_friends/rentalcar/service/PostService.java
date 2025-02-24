package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.PostDto;
import com.road_friends.rentalcar.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
  private final PostMapper postMapper;

  public List<PostDto> getAllPosts() {
    return postMapper.getAllPosts();
  }


  public void savePost(PostDto postDto) {
    postMapper.insertPost(postDto);
  }

  public PostDto getPost(Long id) {
    return postMapper.getPostById(id);
  }

  public void updatePost(PostDto postDto) {
    postMapper.updatePost(postDto);
  }

  public void deletePost(Long id) {
    postMapper.deletePost(id);
  }
}
