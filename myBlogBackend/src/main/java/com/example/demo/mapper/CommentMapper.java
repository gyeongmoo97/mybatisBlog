package com.example.demo.mapper;

import java.time.LocalDateTime;
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

    // 사용자와 관련된 모든 댓글 삭제
    void deleteAllRelatedCommentsByUserId( Long userId);

    // 특정 카테고리에 속한 게시물에 대한 댓글 삭제
    void deleteCommentsByCategoryId( Long categoryId);
  
    //특정 기간 이전에 작성된 모든 댓글 삭제
    void deleteCommentsBeforeDate( LocalDateTime thresholdDate);

    // 특정 상태의 게시물에 대한 댓글 삭제
    void deleteCommentsByPostStatus( String status);
    
    // 대댓글이 있는 경우, 대댓글의 작성자와 댓글의 작성자가 같지 않다면 삭제하지 않음
    void deleteCommentsWithoutChildrenOrSameAuthor( Long userId);

    // 대댓글이 있는 댓글 중 대댓글 작성자가 다른 경우가 있는지 확인
    boolean hasCommentsWithDifferentAuthorChildren(Long userId);

    //다수 댓글 삭제
    void deleteCommentsByPostIds( List<Integer> postIds);

    
}