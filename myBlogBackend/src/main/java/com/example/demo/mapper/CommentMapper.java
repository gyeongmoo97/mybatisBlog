package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Post;

@Repository
@Mapper
public interface CommentMapper {

	int deleteCommentsByPost(Long postId);
}