package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.post.Post;
import com.example.demo.dto.post.PostViewCount;
import com.example.demo.dto.user.UserPostCount;

@Repository
@Mapper
public interface CategoryMapper {

    // 특정 카테고리의 게시물 중 가장 많이 조회된 게시물 조회
    PostViewCount findTopViewedPostByCategory( Long categoryId);

    void updateNoticePostsStatus();

    // 카테고리와 관련된 게시물 카테고리 연관 데이터 삭제
    void deletePostCategoriesByCategoryId( Long categoryId);

    // 카테고리 삭제
    int deleteCategory( Long categoryId);

    // 특정 카테고리의 게시물 삭제
    void deletePostsByCategoryId( Long categoryId);



}