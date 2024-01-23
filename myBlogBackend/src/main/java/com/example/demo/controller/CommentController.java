package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    // 특정 기간 이전에 작성된 모든 댓글 삭제
    @Operation(summary = "5. 오래된 댓글 삭제", description = "특정 기간 이전에 작성된 모든 댓글을 삭제합니다.")
    @DeleteMapping("/old")
    public ResponseEntity<Void> deleteOldComments(
            @Parameter(description = "삭제할 댓글의 기준 날짜", required = true, 
                       example = "2023-01-01T00:00:00", 
                       schema = @Schema(type = "string", format = "date-time")) 
            @RequestParam(name = "thresholdDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) 
            LocalDateTime thresholdDate) {
        commentService.deleteOldComments(thresholdDate);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    // 특정 사용자가 작성한 댓글 삭제
    @Operation(summary = "7. 특정 사용자가 작성한 댓글 삭제", description = "특정 사용자가 작성한 댓글을 삭제합니다. 대댓글이 있는 경우, 대댓글 작성자가 다르면 삭제하지 않습니다.")
    @ApiResponse(responseCode = "200", description = "댓글 삭제 성공")
    @ApiResponse(responseCode = "400", description = "대댓글 작성자가 다른 경우로 인해 삭제 불가")
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteCommentsByUserId(@PathVariable(name = "userId") Long userId) {
        commentService.deleteCommentsByUserId(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
