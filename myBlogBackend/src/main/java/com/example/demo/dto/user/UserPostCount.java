package com.example.demo.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPostCount {
	
   @Schema(description = "사용자 ID", example = "1")
    private Integer userId;

    @Schema(description = "게시물 수", example = "5")
    private Integer postCount;
}
