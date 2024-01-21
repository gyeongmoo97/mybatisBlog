package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.comment.Comment;
import com.example.demo.domain.post.Post;
import com.example.demo.dto.user.UserPostCount;
import com.example.demo.service.CommentService;
import com.example.demo.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/comments") 
public class CommentController {
	
	@Autowired 	
    private UserService userService;
	
	@Autowired 	
    private CommentService commentService;
	
	
	// 특정 게시물에 대한 모든 댓글 조회
    @Operation(summary = "6. 특정 게시물에 대한 모든 댓글 조회", description = "특정 게시물에 달린 모든 댓글을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "댓글 조회 성공")
    @GetMapping("/{postId}") 
    public ResponseEntity<List<Comment>> getCommentsByPost(@PathVariable(name="postId") Long postId) {
        List<Comment> comments = commentService.getCommentsByPost(postId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
	

}
