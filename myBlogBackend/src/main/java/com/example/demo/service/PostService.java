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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        postMapper.createPost(post);
        return post.getPostId(); // MyBatis가 생성한 post_id 반환
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
    
    // 가장 많이 댓글이 달린 상위 n개 게시물 조회
    public List<Post> getTopPosts(int postCount) {
        return postMapper.findTopPosts(postCount);
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
    
    // 특정 게시물의 조회수 증가
    @Transactional
    public boolean incrementViewCount(Long postId) {
        return postMapper.incrementViewCount(postId) > 0;
    }
    
    // 게시물의 상태에 따른 카테고리 변경
    @Transactional
    public void changeCategoryForDrafts() {
        postMapper.changeCategoryForDrafts();
    }
    
    // 비공개(private)' 상태의 게시물 삭제
    @Transactional
    public void deletePostsByStatus(String status) {
        // 댓글 삭제
        commentMapper.deleteCommentsByPostStatus(status);
        // 게시물 카테고리 연관 데이터 삭제
        postCategoriesMapper.deletePostCategoriesByPostStatus(status);
        // 게시물 삭제
        postMapper.deletePostsByStatus(status);
    }
    
    //다수의 게시물 삭제
    @Transactional
    public void deleteMultiplePosts(List<Integer> postIds) {
        // 댓글 삭제
        commentMapper.deleteCommentsByPostIds(postIds);
        // 게시물 카테고리 연관 데이터 삭제
        postCategoriesMapper.deletePostCategoriesByPostIds(postIds);
        // 게시물 삭제
        postMapper.deleteMultiplePosts(postIds);
    }
    
    // 조회수가 낮은 게시물 삭제
    @Transactional
    public void deletePostsWithLowViews(int viewThreshold) {
        // 조회수가 viewThreshold 미만인 게시물의 ID 리스트 조회
        List<Integer> postIds = postMapper.findPostIdsWithViewsLessThan(viewThreshold);
        // 다수의 게시물 삭제 로직 재사용
        deleteMultiplePosts(postIds);
    }
    
    // 특정 날짜 이전에 작성된 게시물 및 댓글 삭제
    @Transactional
    public void deletePostsAndCommentsBeforeDate(LocalDate date) {
        // 특정 날짜 이전에 작성된 게시물의 ID 리스트 조회
        List<Integer> postIds = postMapper.findPostIdsBeforeDate(date);
        // 다수의 게시물 삭제 로직 재사용
        deleteMultiplePosts(postIds);
    }
    
    // 인기 게시물 조회
    public List<Post> getTopPostsAndTopCommentedPosts(int postCount) {

        // 조회 파라미터 초기화
        List<String> sort = new ArrayList<>();
        sort.add("view_count");
        List<String> order = new ArrayList<>();
        order.add("desc"); // 여기를 수정했습니다.
        PostQueryParams queryParams = PostQueryParams.builder()
            .sort(sort)
            .order(order)
            .page((long) postCount * 2)
            .offset((long) 0)
            .build();

        // 조회수가 높은 상위 게시물 조회
        List<Post> topViewedPosts = getTopPosts(postCount * 2);
        // 댓글이 많은 상위 게시물 조회
        List<Post> topCommentedPosts = getPosts(queryParams);

        // 중복 제거 및 최대 postCount개씩만 남기기
        Map<Integer, Post> combinedPosts = new LinkedHashMap<>();
        for (Post post : topViewedPosts) {
            if (combinedPosts.size() < postCount) {
                combinedPosts.put(post.getPostId(), post);
            }
        }
        for (Post post : topCommentedPosts) {
            if (combinedPosts.size() < postCount * 2 && !combinedPosts.containsKey(post.getPostId())) {
                combinedPosts.put(post.getPostId(), post);
            }
        }

        return new ArrayList<>(combinedPosts.values());
    }

}


