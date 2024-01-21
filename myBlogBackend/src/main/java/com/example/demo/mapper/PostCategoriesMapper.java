package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.post.Post;

@Repository
@Mapper
public interface PostCategoriesMapper {

	int deletePostCategoriesByPost(Long postId);

}