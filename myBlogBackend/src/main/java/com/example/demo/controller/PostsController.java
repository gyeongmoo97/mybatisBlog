package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.domain.Post;
import com.example.demo.dto.PostDto;
import com.example.demo.service.PostService;


@RestController
@RequestMapping("/posts")
public class PostsController {

	@Autowired
    private PostService postsService;

    // 게시물 생성 (POST)
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        	postsService.createPost(post);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 게시물 조회 (GET)
    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPost(@PathVariable(name="postId") Long postId) {
        Post post = postsService.getPostById(postId);
       
        if (post != null) {
            return new ResponseEntity<>(post, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 게시물 수정 (PUT)
    @PutMapping("/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable(name="postId") Long postId, @RequestBody Post updatedPost) {
        int postUpdateCount = postsService.updatePost(postId, updatedPost);
        if (postUpdateCount <= 0) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 게시물 삭제 (DELETE)
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