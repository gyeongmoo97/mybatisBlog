package com.example.demo.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.comment.Comment;
import com.example.demo.domain.post.Post;
import com.example.demo.dto.user.UserPostCount;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.mapper.PostCategoriesMapper;
import com.example.demo.mapper.PostMapper;
import com.example.demo.mapper.UserMapper;

import java.util.List;

@Service
public class CommentService {
	
	@Autowired
    private UserMapper userMapper;

	@Autowired
    private PostMapper postMapper;
	
	@Autowired
    private CommentMapper commentMapper;
	
	@Autowired
    private PostCategoriesMapper postCategoriesMapper;

    // 특정 게시물에 대한 모든 댓글 조회
    public List<Comment> getCommentsByPost(Long postId) {
        return commentMapper.findCommentsByPost(postId);
    }
    
    
}


