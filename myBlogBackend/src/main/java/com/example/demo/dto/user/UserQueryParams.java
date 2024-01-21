package com.example.demo.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public class UserQueryParams {

    @Schema(description = "정렬 기준이 될 필드 목록 (예: 'userId', 'createdAt')", example = "[\"userId\", \"createdAt\"]")
    private List<String> sort;

    @Schema(description = "정렬 순서 ('asc' 또는 'desc')", example = "[\"asc\", \"desc\"]")
    private List<String> order;

    @Schema(description = "페이지 번호 (지정하지 않으면 모든 결과 반환)", example = "1")
    private Long page;

    @Schema(description = "페이지 당 항목 수 (지정하지 않으면 기본값 사용)", example = "10")
    private Long offset;

}