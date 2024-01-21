package com.example.demo.domain.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {

    @Schema(description = "사용자 ID", example = "1")
    private Long userId;

    @Schema(description = "사용자 소개", example = "저는 웹 개발자입니다.")
    private String bio;

    @Schema(description = "프로필 사진 URL", example = "https://example.com/profile.jpg")
    private String profilePic;
}
