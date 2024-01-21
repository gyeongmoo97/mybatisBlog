package com.example.demo.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.post.Post;
import com.example.demo.domain.user.UserProfile;
import com.example.demo.dto.user.UserPostCount;
import com.example.demo.dto.user.UserQueryParams;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.mapper.PostCategoriesMapper;
import com.example.demo.mapper.PostMapper;
import com.example.demo.mapper.UserMapper;

import java.util.List;

@Service
public class UserService {
	
	@Autowired
    private UserMapper userMapper;

	@Autowired
    private PostMapper postMapper;
	
	@Autowired
    private CommentMapper commentMapper;
	
	@Autowired
    private PostCategoriesMapper postCategoriesMapper;

	// 특정 사용자의 게시물 조회
    public List<Post> getPostsByUserId(Integer userId) {
        return postMapper.findPostsByUserId(userId);
    }
    
    // 각 사용자별 게시물 수 조회
    public List<UserPostCount> getUserPostCounts() {
        return userMapper.findUserPostCounts();
    }
    
    // 다수의 사용자 조회
    public List<UserProfile> getUsers(UserQueryParams queryParams) {
        return userMapper.findUsers(queryParams);
    }
    
}


