package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Post;
import com.example.demo.service.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


@RestController //컨트롤러가 HTTP 요청을 처리하고, 자바 객체를 HTTP 응답 본문으로 직접 반환하도록 합니다.
/*
 * 1. 기본적인 컨트롤러의 기능 - HTTP 요청을 받아들이는 엔드포인트(endpoints)로 사용
 * 2. 자동 역직렬화(Request Body -> Java Object): 클라이언트로부터 오는 JSON 형식의 HTTP 요청 본문을 자바 객체로 자동으로 변환합니다. 
 * 3. 자동 직렬화(Java Object -> Response Body): 자바 객체를 JSON 형식의 HTTP 응답 본문으로 자동 변환하여 클라이언트에게 보냅니다. 
 * */
@RequestMapping("/posts") // 컨트롤러의 URI 구성
public class PostsController {

	@Autowired 	//의존성 주입
    private PostService postsService;

	 // 게시물 생성 (POST)
    @Operation(summary = "새로운 게시물 생성", description = "새로운 게시물을 생성합니다.")
    @ApiResponse(responseCode = "201", description = "게시물 생성 성공")
    @PostMapping // http method 설정
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        	postsService.createPost(post);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

	// 게시물 조회 (GET)
    @Operation(summary = "게시물 조회", description = "특정 ID를 가진 게시물을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "게시물 조회 성공")
    @ApiResponse(responseCode = "404", description = "게시물을 찾을 수 없음")
    @GetMapping("/{postId}") //같은 경로를 사용하더라도 http mathod로 오버로딩 가능
    public ResponseEntity<Post> getPost(@PathVariable(name="postId") Long postId) {
        Post post = postsService.getPostById(postId);
       
        if (post != null) {
            return new ResponseEntity<>(post, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 게시물 수정 (PUT)
    @Operation(summary = "게시물 수정", description = "특정 ID를 가진 게시물을 수정합니다.")
    @ApiResponse(responseCode = "200", description = "게시물 수정 성공")
    @ApiResponse(responseCode = "404", description = "수정할 게시물을 찾을 수 없음")
    @PutMapping("/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable(name="postId") Long postId, @RequestBody Post updatedPost) {
        int postUpdateCount = postsService.updatePost(postId, updatedPost);
        if (postUpdateCount <= 0) {
        	System.out.println("0개이하");
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
        	System.out.println("0개이상");
        	return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    // 게시물 삭제 (DELETE)
    @Operation(summary = "게시물 삭제", description = "특정 ID를 가진 게시물을 삭제합니다.")
    @ApiResponse(responseCode = "204", description = "게시물 삭제 성공")
    @ApiResponse(responseCode = "404", description = "삭제할 게시물을 찾을 수 없음")
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable(name="postId") Long postId) {
        int postDeleteCount= postsService.deletePost(postId);
        if (postDeleteCount<= 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}