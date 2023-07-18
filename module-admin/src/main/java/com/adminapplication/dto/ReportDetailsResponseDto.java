package com.adminapplication.dto;

import com.core.entity.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportDetailsResponseDto {
    private Long id;
    private String nickname;
    private Category category;
    private String reasonImage;
    private String createdAt;
}
