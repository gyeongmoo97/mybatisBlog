package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.comment.Comment;
import com.example.demo.domain.post.Post;

@Repository
@Mapper
public interface CommentMapper {

	int deleteCommentsByPost(Long postId);

	// 특정 게시물에 대한 모든 댓글 조회
    List<Comment> findCommentsByPost( Long postId);
}