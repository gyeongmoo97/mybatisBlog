package com.example.demo.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.comment.Comment;
import com.example.demo.domain.post.Post;
import com.example.demo.dto.user.UserPostCount;
import com.example.demo.exception.CommentDeletionException;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.mapper.PostCategoriesMapper;
import com.example.demo.mapper.PostMapper;
import com.example.demo.mapper.UserMapper;

import java.time.LocalDateTime;
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
    
    // 특정 기간 이전에 작성된 모든 댓글 삭제
    @Transactional
    public void deleteOldComments(LocalDateTime thresholdDate) {
        commentMapper.deleteCommentsBeforeDate(thresholdDate);
    }
    
    //특정 사용자가 작성한 댓글 삭제
    @Transactional
    public void deleteCommentsByUserId(Long userId) throws CommentDeletionException {
        // 대댓글이 있는 댓글 중 대댓글 작성자가 다른 경우가 있는지 확인
        boolean hasRestrictedComments = commentMapper.hasCommentsWithDifferentAuthorChildren(userId);
        if (hasRestrictedComments) {
            throw new CommentDeletionException("대댓글 작성자가 원 댓글 작성자와 다릅니다.");
        }
        
        // 사용자와 관련된 모든 댓글 삭제
        commentMapper.deleteAllRelatedCommentsByUserId(userId);
    }
}


