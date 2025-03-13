package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.PostDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostMapper {
  @Select("SELECT * FROM posts ORDER BY id DESC LIMIT #{size} OFFSET #{offset}")
  List<PostDto> getAllPosts(@Param("size") int size, @Param("offset") int offset);

  @Insert("INSERT INTO posts (title, content, created_at) VALUES (#{title}, #{content}, CONVERT_TZ(NOW(), 'UTC', 'Asia/Seoul'))")
  void insertPost(PostDto postDto);

  @Select("SELECT * FROM posts WHERE id = #{id}")
  PostDto getPostById(Long id);

  @Update("UPDATE posts SET title = #{title}, content = #{content} WHERE id = #{id}")
  void updatePost(PostDto post);

  @Delete("DELETE FROM posts WHERE id = #{id}")
  void deletePost(Long id);

  @Select("SELECT count(*) FROM posts")
  int countTotal();

  @Select("SELECT * FROM posts WHERE title LIKE CONCAT('%', #{title}, '%') " +
          "ORDER BY id DESC LIMIT #{size} OFFSET #{offset}")
  List<PostDto> findByTitle(@Param("title") String title, @Param("size") int size, @Param("offset") int offset);

  @Select("SELECT COUNT(*) FROM posts WHERE title LIKE CONCAT('%', #{title}, '%')")
  int countByTitle(@Param("title") String title);
}
