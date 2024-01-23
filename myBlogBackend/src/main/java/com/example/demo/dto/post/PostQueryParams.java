package com.example.demo.dto.post;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostQueryParams {

    @Schema(description = "정렬 기준 필드 목록 (예: 'post_id', 'view_count')", example = "[\"post_id\", \"view_count\"]")
    private List<String> sort;

    @Schema(description = "각 정렬 필드에 대한 정렬 순서 ('asc' 또는 'desc')", example = "[\"asc\", \"desc\"]")
    private List<String> order;

    @Schema(description = "페이지당 게시물 수 (null일 경우 모든 게시물을 반환)", example = "10")
    private Long page;

    @Schema(description = "건너뛸 게시물 수 (페이지네이션 시작점)", example = "0")
    private Long offset;
}
