package com.example.demo.dto.post;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostViewCount {
	@Schema(description = "게시물 제목", example = "게시물 예시 제목")
    private String title;

    @Schema(description = "조회수", example = "100")
    private int viewCount;

}
