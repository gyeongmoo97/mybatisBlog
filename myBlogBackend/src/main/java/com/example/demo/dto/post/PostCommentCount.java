package com.example.demo.dto.post;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostCommentCount {
    @Schema(description = "게시물 ID", example = "1")
    private Long postId;

    @Schema(description = "댓글 수", example = "5")
    private int commentCount;

}