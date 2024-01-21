package com.example.demo.domain.post;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Schema(description = "게시물 ID", example = "1", required = true)
    private int postId;

    @Schema(description = "사용자 ID", example = "10", required = true)
    private int userId;

    @Schema(description = "게시물 제목", example = "제목 예시", required = true)
    private String title;

    @Schema(description = "게시물 내용", example = "내용 예시", required = true)
    private String content;

    @Schema(description = "게시물 상태", example = "private", allowableValues = {"public", "draft", "private"})
    private String status;

    @Schema(description = "조회수", example = "0")
    private int viewCount;

    @Schema(description = "생성 시간", example = "2023-01-01T12:00:00")
    private Timestamp createdAt;

    @Schema(description = "수정 시간", example = "2023-01-02T12:00:00")
    private Timestamp updatedAt;
    
    @Schema(description = "카테고리 명", example = "고양이")
    private String categoryName;
}
