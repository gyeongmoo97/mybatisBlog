package com.example.demo.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class PostDto {

    private int postId;
    private int userId;
    private String title;
    private String content;
    private String status;
    private int viewCount;

    // 필요에 따라 추가 필드 및 메소드
}