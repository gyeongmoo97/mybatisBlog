package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Post;

@Repository
@Mapper
public interface PostMapper {

    // 게시물 조회
//    @Select("SELECT * FROM Posts WHERE post_id = #{postId}")
    Post getPostById(Long postId);

	List<Post> getAllPosts();

	int createPost(Post post);

	int updatePost(Post post);

	int deletePost(Long postId);

    // 게시물 목록 조회, 생성, 수정, 삭제 등의 메소드 추가
}