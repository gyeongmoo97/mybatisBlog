package com.example.demo.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.post.Post;
import com.example.demo.dto.post.PostViewCount;
import com.example.demo.dto.user.UserPostCount;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.mapper.PostCategoriesMapper;
import com.example.demo.mapper.PostMapper;
import com.example.demo.mapper.UserMapper;

import java.util.List;

@Service
public class CategoryService {
	
	@Autowired
    private UserMapper userMapper;

	@Autowired
    private PostMapper postMapper;
	
	@Autowired
    private CommentMapper commentMapper;
	
	@Autowired
    private PostCategoriesMapper postCategoriesMapper;
	
	@Autowired
    private CategoryMapper categoryMapper;
	
    // 특정 카테고리의 게시물 중 가장 많이 조회된 게시물 조회
    public PostViewCount getTopViewedPostByCategory(Long categoryId) {
        return categoryMapper.findTopViewedPostByCategory(categoryId);
    }
}


