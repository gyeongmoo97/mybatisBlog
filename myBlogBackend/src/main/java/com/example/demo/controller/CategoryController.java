package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.post.Post;
import com.example.demo.dto.post.PostViewCount;
import com.example.demo.dto.user.UserPostCount;
import com.example.demo.service.CategoryService;
import com.example.demo.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/categories") 
public class CategoryController {
	
	@Autowired 	//의존성 주입
    private UserService userService;
	
	@Autowired 	//의존성 주입
    private CategoryService categoryService;
	
    // 특정 카테고리의 게시물 중 가장 많이 조회된 게시물 조회
    @Operation(summary = "9. 특정 카테고리의 게시물 중 가장 많이 조회된 게시물 조회", description = "특정 카테고리에 속하는 게시물 중 가장 많이 조회된 게시물의 제목과 조회수를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @GetMapping("/top-viewed/{categoryId}")
    public ResponseEntity<PostViewCount> getTopViewedPostByCategory(@PathVariable(name="categoryId") Long categoryId) {
        PostViewCount topViewedPost = categoryService.getTopViewedPostByCategory(categoryId);
        return new ResponseEntity<>(topViewedPost, HttpStatus.OK);
    }
	

}
