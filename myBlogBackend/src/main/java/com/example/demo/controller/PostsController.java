package com.example.demo.controller;

import java.util.List;

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

import com.example.demo.domain.post.Post;
import com.example.demo.dto.post.PostCommentCount;
import com.example.demo.dto.post.PostQueryParams;
import com.example.demo.service.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;


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
    
    // 공개된 모든 게시물 조회
    @Operation(summary = "1. 공개된 모든 게시물 조회", description = "사용자 ID에 해당하는 모든 게시물의 제목과 생성 날짜를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "게시물 조회 성공")
    @ApiResponse(responseCode = "404", description = "게시물을 찾을 수 없음")
    @GetMapping("/public")
    public ResponseEntity<List<Post>> getAllPublicPosts() {
        List<Post> publicPosts = postsService.getPublicPosts();
        if (!publicPosts.isEmpty()) {
            return new ResponseEntity<>(publicPosts, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
    // 가장 많이 조회된 상위 5개 게시물 조회
    @Operation(summary = "3. 인기 게시물 조회", description = "가장 많이 조회된 상위 5개 게시물을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "게시물 조회 성공")
    @GetMapping("/top5")
    public ResponseEntity<List<Post>> getTop5Posts() {
        List<Post> topPosts = postsService.getTop5Posts();
        return new ResponseEntity<>(topPosts, HttpStatus.OK);
    }
    
    // 특정 카테고리에 속하는 게시물 조회
    @Operation(summary = "4. 카테고리별 게시물 조회", description = "특정 카테고리에 속하는 모든 게시물을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "게시물 조회 성공")
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Post>> getPostsByCategory(@PathVariable(name="categoryId") Integer categoryId) {
        List<Post> posts = postsService.getPostsByCategory(categoryId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
    
    // 특정 사용자의 최근 게시물 조회
    @Operation(summary = "7. 사용자별 최근 게시물 조회", description = "특정 사용자가 최근에 작성한 게시물 3개를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "게시물 조회 성공")
    @GetMapping("/recent/{userId}")
    public ResponseEntity<List<Post>> getRecentPostsByUser(@PathVariable(name="userId") Long userId) {
        List<Post> recentPosts = postsService.getRecentPostsByUser(userId);
        return new ResponseEntity<>(recentPosts, HttpStatus.OK);
    }

    // 각 게시물별 댓글 수 조회
    @Operation(summary = "8. 각 게시물별 댓글 수 조회", description = "모든 게시물과 해당 게시물에 달린 댓글의 수를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @GetMapping("/comments/count")
    public ResponseEntity<List<PostCommentCount>> getPostCommentCounts() {
        List<PostCommentCount> postCommentCounts = postsService.getPostCommentCounts();
        return new ResponseEntity<>(postCommentCounts, HttpStatus.OK);
    }
    
    // 특정 기간 동안 작성된 게시물 조회
    @Operation(summary = "10. 한 달 내 작성된 게시물 조회", description = "지난 한 달 동안 작성된 모든 게시물의 제목과 작성 시간을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @GetMapping("/recent")
    public ResponseEntity<List<Post>> getPostsFromLastMonth() {
        List<Post> recentPosts = postsService.getPostsFromLastMonth();
        return new ResponseEntity<>(recentPosts, HttpStatus.OK);
    }
    
    // 다수의 게시물 조회
    @Operation(summary = "11. 다수의 게시물 조회", description = "정렬 기준과 페이지네이션 옵션을 사용하여 다수의 게시물을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @PostMapping("/multiple")
    public ResponseEntity<List<Post>> getPosts(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "다수의 게시물 조회를 위한 파라미터",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = PostQueryParams.class),
                    examples = @ExampleObject(
                        name = "Example",
                        value = "{\n" +
                                "  \"sort\": [\"post_id\", \"view_count\"],\n" +
                                "  \"order\": [\"asc\", \"desc\"],\n" +
                                "  \"page\": 10,\n" +
                                "  \"offset\": 3\n" +
                                "}"
                    )
                )
            ) @RequestBody PostQueryParams queryParams) {
        List<Post> posts = postsService.getPosts(queryParams);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

}