package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Data
//@AllArgsConstructor
//@Getter
//@Setter
//@NoArgsConstructor
//@ToString
public class Post {
    private int postId;
    private int userId;
    private String title;
    private String content;
    private String status;
    private int viewCount;
    private Timestamp createdAt;
    private Timestamp updatedAt;



    // 필요한 경우, 추가적인 메소드나 로직
}
