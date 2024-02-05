package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.post.PostViewCount;
import com.example.demo.service.CategoryService;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

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
	
    // 특정 카테고리의 모든 게시물 상태 변경
    @Operation(summary = "2. 공지사항 게시물 상태 변경", description = "'공지사항' 카테고리에 속한 모든 게시물을 '공개(public)' 상태로 변경합니다.")
    @ApiResponse(responseCode = "200", description = "상태 변경 성공")
    @PostMapping("/notice-posts/update-status")
    public ResponseEntity<Void> updateNoticePostsStatus() {
        categoryService.updateNoticePostsStatus();
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    // 특정 카테고리 삭제
    @Operation(summary = "2. 특정 카테고리 삭제", description = "지정된 카테고리와 그에 속한 모든 게시물을 삭제합니다.")
    @ApiResponse(responseCode = "200", description = "카테고리 삭제 성공")
    @ApiResponse(responseCode = "404", description = "카테고리를 찾을 수 없음")
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable(name="categoryId") Long categoryId) {
        boolean deleted = categoryService.deleteCategory(categoryId);
        return deleted ? new ResponseEntity<>(HttpStatus.OK) 
                       : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    // 특정 카테고리에 속한 게시물 삭제
    @Operation(summary = "3. 특정 카테고리 게시물 삭제", description = "지정된 카테고리에 속한 모든 게시물을 삭제합니다.")
    @ApiResponse(responseCode = "200", description = "게시물 삭제 성공")
    @DeleteMapping("/{categoryId}/posts")
    public ResponseEntity<Void> deletePostsByCategory(@PathVariable (name="categoryId") Long categoryId) {
        categoryService.deletePostsByCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
