package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.post.Post;

@Repository
@Mapper
public interface PostCategoriesMapper {

	int deletePostCategoriesByPost(Long postId);
	
	// 게시물 카테고리 연관 데이터 삭제
    void deletePostCategoriesByPostStatus(String status);

    // 다수 게시물 카테고리 연관 데이터 삭제
    void deletePostCategoriesByPostIds( List<Integer> postIds);

}