package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.PageDto;
import com.road_friends.rentalcar.dto.PostDto;
import com.road_friends.rentalcar.mapper.ApiPostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApiPostService {
  private final ApiPostMapper apiPostMapper;

  public PageDto<PostDto> getAllPosts(int page, int size) {
    int totalElements = apiPostMapper.getTotalPosts();
    int offset = (page - 1) * size;
    List<PostDto> postDtos = apiPostMapper.getPagedPosts(offset, size);
    PageDto<PostDto> pageDto = new PageDto<>(page, size, totalElements, postDtos);
    return pageDto;
  }

  public void savePost(PostDto postDto) {
    apiPostMapper.insertPost(postDto);
  }

  public PostDto getPost(Long id) {
    return apiPostMapper.getPostById(id);
  }

  public void updatePost(PostDto postDto) {
    apiPostMapper.updatePost(postDto);
  }

  public void deletePost(Long id) {
    apiPostMapper.deletePost(id);
  }
}
