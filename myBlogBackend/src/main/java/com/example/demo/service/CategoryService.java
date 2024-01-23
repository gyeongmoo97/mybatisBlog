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
    
    // 특정 카테고리의 모든 게시물 상태 변경
    @Transactional
    public void updateNoticePostsStatus() {
        categoryMapper.updateNoticePostsStatus();
    }
    
    // 특정 카테고리 삭제
    @Transactional
    public boolean deleteCategory(Long categoryId) {
        // 카테고리와 관련된 게시물 카테고리 연관 데이터 삭제
        categoryMapper.deletePostCategoriesByCategoryId(categoryId);

        // 카테고리 삭제
        return categoryMapper.deleteCategory(categoryId) > 0;
    }
    
    // 특정 카테고리에 속한 게시물 삭제
    @Transactional
    public void deletePostsByCategory(Long categoryId) {
        // 카테고리에 속한 게시물의 댓글 삭제
        commentMapper.deleteCommentsByCategoryId(categoryId);

        // 카테고리에 속한 게시물의 카테고리 연관 데이터 삭제
        categoryMapper.deletePostCategoriesByCategoryId(categoryId);

        // 카테고리에 속한 게시물 삭제
        categoryMapper.deletePostsByCategoryId(categoryId);
     }
}


