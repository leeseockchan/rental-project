package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.PostDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ApiPostMapper {
  @Select("SELECT COUNT(*) FROM posts")
  int getTotalPosts();

  @Select("SELECT id, title, content, created_at FROM posts ORDER BY id DESC LIMIT #{size} OFFSET #{offset}")
  List<PostDto> getPagedPosts(@Param("offset") int offset, @Param("size") int size);

  @Insert("INSERT INTO posts (title, content) VALUES (#{title}, #{content})")
  void insertPost(PostDto postDto);

  @Select("SELECT * FROM posts WHERE id = #{id}")
  PostDto getPostById(Long id);

  @Update("UPDATE posts SET title = #{title}, content = #{content} WHERE id = #{id}")
  void updatePost(PostDto post);

  @Delete("DELETE FROM posts WHERE id = #{id}")
  void deletePost(Long id);
}
