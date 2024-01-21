package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.post.Post;
import com.example.demo.domain.user.UserProfile;
import com.example.demo.dto.user.UserPostCount;
import com.example.demo.dto.user.UserQueryParams;
import com.example.demo.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;


import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


@RestController
@RequestMapping("/users") 
public class UserController {
	
	@Autowired 	//의존성 주입
    private UserService userService;
	
	// 특정 사용자의 게시물 조회
    @Operation(summary = "2. 특정 사용자의 게시물 조회", description = "사용자 ID에 해당하는 모든 게시물의 제목과 생성 날짜를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "게시물 조회 성공")
    @ApiResponse(responseCode = "404", description = "게시물을 찾을 수 없음")
    @GetMapping("/{userId}/posts")
    public ResponseEntity<List<Post>> getUserPosts(
            @Parameter(description = "조회할 사용자의 ID", required = true, schema = @Schema(type = "integer"))
            @PathVariable(name="userId") Integer userId) {
        List<Post> userPosts = userService.getPostsByUserId(userId);
        if (!userPosts.isEmpty()) {
            return new ResponseEntity<>(userPosts, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // 각 사용자별 게시물 수 조회
    @Operation(summary = "5. 각 사용자별 게시물 수 조회", description = "각 사용자별로 작성한 게시물의 수를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @GetMapping("/posts/count")
    public ResponseEntity<List<UserPostCount>> getUserPostCounts() {
        List<UserPostCount> userPostCounts = userService.getUserPostCounts();
        return new ResponseEntity<>(userPostCounts, HttpStatus.OK);
    }
    
    // 다수의 사용자 조회
    @Operation(summary = "12. 다수의 사용자 조회", description = "정렬 기준과 페이지네이션 옵션을 사용하여 다수의 사용자를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @PostMapping("/multiple")
    public ResponseEntity<List<UserProfile>> getUsers(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "다수의 사용자 조회를 위한 파라미터",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = UserQueryParams.class),
                    examples = @ExampleObject(
                        name = "Example",
                        value = "{\n" +
                                "  \"sort\": [\"userId\", \"createdAt\"],\n" +
                                "  \"order\": [\"asc\", \"desc\"],\n" +
                                "  \"page\": 10,\n" +
                                "  \"offset\": 3\n" +
                                "}"
                    )
                )
            ) @RequestBody UserQueryParams queryParams) {
        List<UserProfile> users = userService.getUsers(queryParams);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
	

}
