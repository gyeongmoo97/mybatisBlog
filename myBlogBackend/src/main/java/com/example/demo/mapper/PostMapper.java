package com.example.demo.mapper;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.post.Post;
import com.example.demo.dto.post.PostCommentCount;
import com.example.demo.dto.post.PostQueryParams;

@Repository
@Mapper
public interface PostMapper {



    Post getPostById(Long postId);

	List<Post> getAllPosts();

	int createPost(Post post);

	int updatePost(Post post);

	int deletePost(Long postId);
	
	
	//공개된 모든 게시물 조회 
	List<Post> findAllPublicPosts();
	
	// 특정 사용자의 게시물 조회
    List<Post> findPostsByUserId(Integer userId);

    // 가장 많이 조회된 상위 5개 게시물 조회
    List<Post> findTop5Posts();
    
    // 특정 카테고리에 속하는 게시물 조회
    List<Post> findPostsByCategory( Integer categoryId);
    
    // 특정 사용자의 최근 게시물 조회
    List<Post> findRecentPostsByUser( Long userId);

    // 각 게시물별 댓글 수 조회
    List<PostCommentCount> findPostCommentCounts();
    
    // 특정 기간 동안 작성된 게시물 조회
    List<Post> findPostsFromLastMonth();

    // 다수의 게시물 조회
    List<Post> findPosts(PostQueryParams queryParams);

    // 특정 게시물의 조회수 증가
    int incrementViewCount(Long postId);

    void changeCategoryForDrafts();

    // 사용자와 관련된 게시물 삭제
    void deletePostsByUserId( Long userId);

    // 사용자와 관련된 게시물 카테고리 삭제
    void deletePostCategoriesByUserId( Long userId);

    // 해당 상태의 게시물 삭제
    void deletePostsByStatus( String status);

    // 먼저 "postcategories" 테이블에서 참조를 삭제
    void deletePostCategoriesByStatus( String status);

    //다수의 게시물 삭제
    void deleteMultiplePosts( List<Integer> postIds);

    // 조회수가 낮은 게시물 삭제
    List<Integer> findPostIdsWithViewsLessThan(int viewThreshold);

    //특정 날짜 이전에 작성된 게시물 조회
    List<Integer> findPostIdsBeforeDate(LocalDate date);

}