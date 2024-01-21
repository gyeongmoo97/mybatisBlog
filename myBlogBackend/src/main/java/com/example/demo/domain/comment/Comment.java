package com.example.demo.domain.comment;

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
public class Comment {

    @Schema(description = "댓글 ID", example = "1", required = true)
    private Long commentId;

    @Schema(description = "게시물 ID", example = "10", required = true)
    private Long postId;

    @Schema(description = "사용자 ID", example = "5", required = true)
    private int userId;

    @Schema(description = "부모 댓글 ID", example = "3")
    private Long parentCommentId;

    @Schema(description = "댓글 내용", example = "댓글 예시", required = true)
    private String comment;

    @Schema(description = "생성 시간", example = "2023-01-01T12:00:00")
    private Timestamp createdAt;
}