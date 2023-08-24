package com.adminapplication.dto;

import com.core.entity.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportDetailsResponseDto {
    private Long id;
    private String nickname; // 게시글 작성자 닉네임
    private Long userId; // 신고자
    private Category category; // 신고 사유
    private String reasonImage; // 신고 이미지
    private String createdAt;
}
