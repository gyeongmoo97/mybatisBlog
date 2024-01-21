package com.example.demo.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.post.Post;
import com.example.demo.dto.post.PostCommentCount;
import com.example.demo.dto.post.PostQueryParams;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.mapper.PostCategoriesMapper;
import com.example.demo.mapper.PostMapper;

import java.util.List;

@Service
public class PostService {

	@Autowired
    private PostMapper postMapper;
	
	@Autowired
    private CommentMapper commentMapper;
	
	@Autowired
    private PostCategoriesMapper postCategoriesMapper;

    // 게시물 조회 메서드
    public Post getPostById(Long postId) {
        return postMapper.getPostById(postId);
    }

    // 모든 게시물 조회 메서드
    public List<Post> getAllPosts() {
        return postMapper.getAllPosts();
    }

    // 게시물 생성 메서드
    public int createPost(Post post) {
        return postMapper.createPost(post);
    }

    // 게시물 수정 메서드
    public int updatePost(Long postId, Post post) {
        
		return postMapper.updatePost(post);
    }

    // 게시물 삭제 메서드
    @Transactional // all or not 원칙으로 하나라도 구문이 실패하면 모두 롤백시킵니다.
    public int deletePost(Long postId) {
    	commentMapper.deleteCommentsByPost(postId);
    	postCategoriesMapper.deletePostCategoriesByPost(postId);
        return postMapper.deletePost(postId);

    }
    
    // 공개된 모든 게시물 조회
    public List<Post> getPublicPosts() {
        return postMapper.findAllPublicPosts();
    }
    
    // 가장 많이 조회된 상위 5개 게시물 조회
    public List<Post> getTop5Posts() {
        return postMapper.findTop5Posts();
    }
    
    // 특정 카테고리에 속하는 게시물 조회
    public List<Post> getPostsByCategory(Integer categoryId) {
        return postMapper.findPostsByCategory(categoryId);
    }
    
    // 특정 사용자의 최근 게시물 조회
    public List<Post> getRecentPostsByUser(Long userId) {
        return postMapper.findRecentPostsByUser(userId);
    }
    
    // 각 게시물별 댓글 수 조회
    public List<PostCommentCount> getPostCommentCounts() {
        return postMapper.findPostCommentCounts();
    }
    
    // 특정 기간 동안 작성된 게시물 조회
    public List<Post> getPostsFromLastMonth() {
        return postMapper.findPostsFromLastMonth();
    }
    
    // 다수의 게시물 조회
    public List<Post> getPosts(PostQueryParams queryParams) {
        return postMapper.findPosts(queryParams);
    }
}


