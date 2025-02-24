package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.PostDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostMapper {
  @Select("SELECT * FROM posts ORDER BY created_at DESC")
  List<PostDto> getAllPosts();

  @Insert("INSERT INTO posts (title, content) VALUES (#{title}, #{content})")
  void insertPost(PostDto postDto);

  @Select("SELECT * FROM posts WHERE id = #{id}")
  PostDto getPostById(Long id);

  @Update("UPDATE posts SET title = #{title}, content = #{content} WHERE id = #{id}")
  void updatePost(PostDto post);

  @Delete("DELETE FROM posts WHERE id = #{id}")
  void deletePost(Long id);
}
