package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.post.Post;
import com.example.demo.domain.user.UserProfile;
import com.example.demo.dto.user.UserPostCount;
import com.example.demo.dto.user.UserQueryParams;

@Repository
@Mapper
public interface UserMapper {

	// 특정 사용자의 게시물 조회
    List<Post> findPostsByUserId(Integer userId);
    
    // 각 사용자별 게시물 수 조회
    List<UserPostCount> findUserPostCounts();

    // 다수의 사용자 조회
    List<UserProfile> findUsers(UserQueryParams queryParams);

    int deleteUser( Long userId);
}